
document.addEventListener('DOMContentLoaded', () => {
    const premade = document.getElementById('premade');
    const toppings = document.getElementById('toppings');
    const tools = document.getElementById('tools');
    const ordertally = document.getElementById('ordertally');
    const salary = document.getElementById('salary');
    const signout = document.getElementById('signout');
    const orderButton = document.getElementById('order');
    const education = document.getElementById('education');
    const name = document.getElementById('name');
    // constrains the salary to 2 decimal places
    salary.addEventListener('input', function() {
        this.value = this.value.replace(/(\.\d\d)\d+|([\d.]*)[^\d.]/, '$1$2');
    });
    const processLanguages = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <label class="form-check-label">${data.language}
            <input class="form-check-input languages" value="${data.language}" name="${data.language}" type="checkbox"> 
            </label>
            </div>`
        }).join("");
//        Adds the element after the last child of the element selected
        toppings.insertAdjacentHTML("beforeend",html);
    }
    const processTools = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <label class="form-check-label">${data.tool}
            <input class="form-check-input tools" value="${data.tool}" name="${data.tool}" type="checkbox"> 
            </label>
            </div>`
        }).join("");
//        Adds the element after the last child of the element selected
        tools.insertAdjacentHTML("beforeend",html);
    }
    const processPremades = (data) => {
        const html = data.map(data => {
            return `<option value="${data.premade}">${data.premade}</option>`
        }).join("");
//        Adds the element after the last child of the element selected
        premade.insertAdjacentHTML("beforeend",html);
    }

    fetch('/start-order/', {
        method: 'GET'
        })
        .then(response => {
            if (response.status === 401) {
                window.location.href = '../index.html';
            } else if(!response.ok) {
                throw Error("Error", response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            processLanguages(data[0].languages);
            processTools(data[0].tools);
            processPremades(data[0].premades);
            addListenerInputs();
        })
        .catch(error => {
            console.log(error);
        
    });
    signout.addEventListener('click', () => {
        logout();
    });

    orderButton.addEventListener('click', () => {
        placeOrder();
    });

    // Add listeners to inputs to update the order tally
    addListenerInputs();
    function addListenerInputs() {    
        const inputs = document.querySelectorAll('input');
        for (const input of inputs) {
        input.addEventListener('click', (event) => {
            const elem = event.currentTarget;
            if (elem.checked) {
                let label = elem.parentNode.textContent;
                let tally = `<li class="${label}" class="list-group-item">${label}</li>`;
                ordertally.insertAdjacentHTML("beforeend",tally);
            } else if (elem.checked === false) {
                let label = elem.parentNode.textContent;
                let removeLi = document.getElementById(label);
                if (removeLi) {
                    removeLi.remove();
                }
            }
        })
        }
    };

    
    const logout = () => {
        fetch(`/logout/`, {
            method: `POST`,
        }).then((res) => {
            if (!res.ok) {
                throw Error("Error", res.status);
            } else {
            window.location.href = '../index.html';
            }
        })
    }
    const placeOrder = () => {
        let languagesArr = [];
        fillArray(languagesArr, 'languages');
        console.log(JSON.stringify(languagesArr));
        let toolsArr = [];
        fillArray(toolsArr, 'tools');
        const order = {
            name: name.value,
            premade: premade.value,
            education: education.value.toupperCase(),
            salary: salary.value,
            languages: languagesArr,
            tools: toolsArr,
        }

        console.log(JSON.stringify(order));
        // fetch(`/order/`, {
        //     method: 'POST',
        //     body: JSON.stringify(order),
        //     credentials: 'include'
        // }).then((res) => {
        //     if (res.status === 204) {
        //         window.location.href = '/pages/userprofile.html';
        //     } else {
        //         alert('Invalid username or password');
        //     }
        // });
    }

    fillArray = (arr, className) => {
        let inputs = document.querySelectorAll(`.${className}`);
        for (const input of inputs) {
            if (input.checked) {
                arr.push(input.value);
            }
        }
    }
    
});



