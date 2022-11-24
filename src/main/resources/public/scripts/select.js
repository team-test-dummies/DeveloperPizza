export {deselectAll, selectByValue, parseSelect}

function deselectAll(selectElement) {
    for (const option of selectElement.options) {
        option.removeAttribute("selected")
    }
}

function selectByValue(selectElement, value) {
    for (const option of selectElement.options) {
        if (option.getAttribute("value") == value) {
            option.toggleAttribute("selected")
            break
        }
    }
}

function parseSelect(selectElement) {
    if (selectElement.hasAttribute("multiple")) {
        const result = []
        for (const option of selectElement.selectedOptions) {
            result.push(option.value)
        }
        return result
    }
    else {
        return selectElement.selectedOptions[0].value
    }
}