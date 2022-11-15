document.addEventListener('DOMContentLoaded', () => {
    const premade = document.getElementById('premade');
    const toppings = document.getElementById('toppings');
    const tools = document.getElementById('tools');
    const ordertally = document.getElementById('ordertally');

    const processData = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <input class="form-check-input" type="checkbox" value="" id="topping-id"> 
            <label class="form-check-label" for="topping-id">${data.topping}
            </label>
            </div>`
        }).join("");
        toppings.insertAdjacentHTML("beforeend",html);
    }

    fetch('http://127.0.01:8080/start-order/', {
        method: 'POST'})
        .then(response => {
        // added for 
        if (!response.ok) {
            throw Error("Error ", response.status)
        }
        return response.json();
        })
        .then(data => {
            console.log(data);
            processData(data);

        })
        .catch(error => {
            console.log(error);
    }); 
});