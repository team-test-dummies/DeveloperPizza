import templatesCollection from "/scripts/TemplatesCollector.js"
import {deselectAll, selectByValue, parseSelect} from "/scripts/select.js"
import {putOrder} from "/scripts/fetches.js"
import {replaceOrder} from "/scripts/userprofile.js"

class OrderEditor extends HTMLElement {
    constructor() {
        super()
        this.dialog = document.createElement("dialog")

        const orderIdLabel = document.createElement("label")
        this.orderId = document.createElement("output")
        orderIdLabel.append(
            document.createTextNode("Order ID:"),
            this.orderId
        )

        const nameLabel = document.createElement("label")
        this.name = document.createElement("input")
        nameLabel.append(
            document.createTextNode("Name:"),
            this.name
        )

        const languageLabel = document.createElement("label")
        this.languages = document.createElement("select")
        this.languages.toggleAttribute("multiple")
        templatesCollection.languages.forEach(language => {
            const option = document.createElement("option")
            option.setAttribute("value", language)
            option.innerText = language;
            this.languages.appendChild(option);
        })
        languageLabel.append(
            document.createTextNode("Language(s):"),
            this.languages
        )

        const toolLabel = document.createElement("label")
        this.tools = document.createElement("select")
        this.tools.toggleAttribute("multiple")
        templatesCollection.tools.forEach(tool => {
            const option = document.createElement("option")
            option.setAttribute("value", tool)
            option.innerText = tool;
            this.tools.appendChild(option);
        })
        toolLabel.append(
            document.createTextNode("Tool(s):"),
            this.tools
        )

        const educationLabel = document.createElement("label")
        this.educations = document.createElement("select")
        templatesCollection.educations.forEach(education => {
            const option = document.createElement("option")
            option.setAttribute("value", education)
            option.innerText = education;
            this.educations.appendChild(option);
        })
        educationLabel.append(
            document.createTextNode("Education Requirement:"),
            this.educations
        )

        const salaryLabel = document.createElement("label")
        this.salary = document.createElement("input")
        this.salary.setAttribute("type", "number")
        salaryLabel.append(
            document.createTextNode("Salary:"),
            this.salary
        )

        const userIdLabel = document.createElement("label")
        this.userId = document.createElement("output")
        userIdLabel.append(
            document.createTextNode("User ID:"),
            this.userId
        )

        this.confirmButton = document.createElement("button")
        this.confirmButton.innerText = "Confirm"
        this.confirmButton.addEventListener(
            "click",
            this.confirm.bind(this)
        )

        this.formElements = [
            this.orderId,
            this.name,
            this.languages,
            this.tools,
            this.educations,
            this.salary,
            this.confirmButton
        ]


        this.dialog.append(
            orderIdLabel,
            nameLabel,
            languageLabel,
            toolLabel,
            educationLabel,
            salaryLabel,
            userIdLabel,
            this.confirmButton
        )

        this.append(
            this.dialog
        )

        // needs refactor
        this.dialog.addEventListener("close", event => {
          replaceOrder(this.order)
        })
    }

    get order() {
        const order = {}
        order.id = Number.parseInt(this.orderId.innerText)
        order.name = this.name.value
        order.languages = parseSelect(this.languages)
        order.tools = parseSelect(this.tools)
        order.educationRequirement = parseSelect(this.educations)
        order.salary = Number.parseInt(this.salary.value)
        order.userId = Number.parseInt(this.userId.value)
        return order
    }

    set order(value) {
        this.formElements.forEach(element => {
            element.disabled = false
        })
        this.orderId.innerText = value.id
        this.name.value = value.name
        deselectAll(this.languages)
        value.languages.forEach(language => {
            selectByValue(this.languages, language)
        })
        deselectAll(this.tools)
        value.tools.forEach(tool => {
            selectByValue(this.tools, tool)
        })
        deselectAll(this.educations)
        selectByValue(this.educations, value.educationRequirement)
        this.salary.value = value.salary
        this.userId.innerText = value.userId
    }

    async confirm() {
        this.formElements.forEach(element => {
            element.disabled = true
        })
        try {
            await putOrder(this.order)
            this.close()
        }
        catch (error) {
            this.formElements.forEach(element => {
                element.disabled = false
            })
            console.error(error)
        }
    }

    open(order) {
        this.order = order
        this.dialog.showModal()
    }

    close() {
        this.dialog.close()
    }

}

customElements.define("order-editor", OrderEditor)
