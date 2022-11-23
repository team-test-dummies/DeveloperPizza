import {logout, getOrders, putOrder, deleteOrder} from "/scripts/fetches.js"

// below is actually what runs when a user navigates to the page

const signout = document.getElementById('signout');
signout.addEventListener('click', logout)

const accountInfo = document.getElementById('accountInfo');
getUser().then(data => {
    const ul = createUserBlock(data)
    accountInfo.appendChild(ul)
})

const orders = new Map()
const ordersInfo = document.getElementById('orders')
const orderEditor = document.querySelector("order-editor")
getOrders().then(datas => {
    datas.forEach(data => {
        orders.set(data.id, data)
    })
    const orderNodes = datas.map(data => createOrderBlock(data))
    ordersInfo.append(...orderNodes)
})

// function definitions below

async function getUser() {
    const response = await fetch(`/user/`, {
        method: `GET`,
        credentials: `include`
    })
    if (response.status === 401) {
        location = "/";
    }
    else if(!response.ok) {
        throw Error("Error", response.status);
    }
    else {
        return await response.json()
    }
}

function createUserBlock(data) {
    const ul = document.createElement("ul");
    ul.classList.add("list-unstyled")
    const li = document.createElement("li");
    li.classList.add("list-group-item");

    const usernameLi = li.cloneNode(true)
    usernameLi.textContent = data.username

    const accountNameLi = li.cloneNode(true)
    accountNameLi.textContent = data.accountName

    const phoneNumberLi = li.cloneNode(true)
    phoneNumberLi.textContent = data.phoneNumber

    const emailLi = li.cloneNode(true)
    emailLi.textContent = data.email

    const locationLi = li.cloneNode(true)
    locationLi.textContent = data.location

    ul.append(
        usernameLi,
        accountNameLi,
        phoneNumberLi,
        emailLi,
        locationLi
     )

     return ul
}

function createOrderBlock(order) {
    const topLi = document.createElement("li")
    topLi.dataset.id = order.id
    topLi.classList.add("list-group-item")
    const ul = document.createElement("ul")
    ul.classList.add("list-group")

    const li = document.createElement("li")
    li.classList.add("list-group-item")
    const label = document.createTextNode("")
    const output = document.createElement("output")
    li.append(label, output)

    label.textContent = "Order ID:"
    output.textContent = order.id
    output.className = "order-id"
    const orderIdLi = li.cloneNode(true)

    label.textContent = "Name:"
    output.textContent = order.name
    output.className = "name"
    const nameLi = li.cloneNode(true)

    label.textContent = "Language(s):"
    output.textContent = order.languages.join(", ")
    output.className = "languages"
    const languagesLi = li.cloneNode(true)

    label.textContent = "Tool(s):"
    output.textContent = order.tools.join(", ")
    output.className = "tools"
    const toolsLi = li.cloneNode(true)

    label.textContent = "Education Requirement:"
    output.textContent = order.educationRequirement
    output.className = "education"
    const educationLi = li.cloneNode(true)

    label.textContent = "Salary:"
    output.textContent = order.salary
    output.className = "salary"
    const salaryLi = li.cloneNode(true)

    const buttonLi = document.createElement("li")
    buttonLi.classList.add("list-group-item")
    const editButton = document.createElement("button")
    editButton.classList.add("btn", "btn-primary", "w-45")
    editButton.textContent = "Edit Order"
    const deleteButton = document.createElement("button")
    deleteButton.classList.add("btn", "btn-primary", "w-45")
    deleteButton.textContent = "Delete Order"
    buttonLi.append(editButton, deleteButton)

    editButton.addEventListener("click", event => {
        orderEditor.open(orders.get(order.id))
    })

    deleteButton.addEventListener("click", event => {
        deleteOrder(order.id).then(result => topLi.remove())
    })

    ul.append(
        orderIdLi,
        nameLi,
        languagesLi,
        toolsLi,
        educationLi,
        salaryLi,
        buttonLi
    )
    topLi.appendChild(ul)
    return topLi
}

export function replaceOrder(order) {
    orders.set(order.id, order)
    const orderNode = document.querySelector(`li[data-id='${order.id}']`)
    const orderId = orderNode.querySelector(".order-id")
    const name = orderNode.querySelector(".name")
    const languages = orderNode.querySelector(".languages")
    const tools = orderNode.querySelector(".tools")
    const education = orderNode.querySelector(".education")
    const salary = orderNode.querySelector(".salary")

    orderId.innerText = order.id
    name.innerText = order.name
    languages.innerText = order.languages.join(", ")
    tools.innerText = order.tools.join(", ")
    education.innerText = order.educationRequirement
    salary.innerText = order.salary
}


