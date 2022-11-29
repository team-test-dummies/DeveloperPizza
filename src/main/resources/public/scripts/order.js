
document.addEventListener('DOMContentLoaded', () => {
    const ordertally = document.getElementById('ordertally');
    const signout = document.getElementById('signout');
    const orderButton = document.getElementById('order');
    const cancelButton = document.getElementById('cancel');
    const placeOrderButton = document.getElementById('place-order');
    const toppings = document.getElementById('toppings');
    const tools = document.getElementById('tools');
    const name = document.getElementById('name');

    let templateData = {};
    cancelButton.addEventListener('click', () => {
        closeModal();
    });

    signout.addEventListener('click', () => {
        logout();
    });

    orderButton.addEventListener('click', () => {
        order();
    });

    const modal = document.getElementById('orderModal');
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
        const premade = document.getElementById('premade');
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
            processLanguages(data[0].languages);
            processTools(data[0].tools);
            processPremades(data[0].premades);
            addListenerInputs();
            return fetch('/templates/', {
                method: 'GET'
            })
        }).then (response => {
            if (!response.ok) {
                throw Error("Error", response.status);
            } else {
                return response.json();
            }
        }).then (data => {
            processTemplatesData(data);
        })
        .catch(error => {
            console.log(error);
        
    });

    // EVENT LISTENER FOR SELECTING PREMADE
    premade.addEventListener('change', () => {
        if (premade.value === 'none') {
        } else {
            clearCheck("languages");
            clearCheck("tools");
            clearTally();
            processTemplate(premade.value);
        }
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
                let tally = `<li id="${label}" class="list-group-item">${label}</li>`;
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
    
    const order = () => {
        const education = document.getElementById('education');
        const salary = document.getElementById('salary');
        let languagesArr = [];
        fillArray(languagesArr, 'languages');
        let toolsArr = [];
        fillArray(toolsArr, 'tools');
        userId = Number.parseInt(sessionStorage.getItem("id"));
        const order = {
            name: name.value,
            educationRequirement: education.value,
            salary: parseInt(salary.value),
            languages: languagesArr,
            tools: toolsArr,
            userId: userId
        }
        openModal(order);
        placeOrderButton.addEventListener('click', () => {
            placeOrder(order);
        });
    }

    const placeOrder = (order) => {        
        fetch(`/orders/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(order)
        }).then((res) => {
            if (res.status === 201 || res.status === 200) {
                window.location.href = '/pages/userprofile.html';
            } else {
                alert("Error placing order");
                console.log(res.status);
            }
        }).catch(
            err => console.log(err)
        );
    }

    fillArray = (arr, className) => {
        let inputs = document.querySelectorAll(`.${className}`);
        for (const input of inputs) {
            if (input.checked) {
                arr.push(input.value);
            }
        }
    }

    openModal= (order) => {
        console.log(order);
        const modalHTML = `<ul class="list-group">
            <li class="list-group-item active">Order Summary</li>
            <li class="list-group-item">Name: ${order.name}</li>
            <li class="list-group-item">Education: ${order.educationRequirement}</li>
            <li class="list-group-item">Salary: $${order.salary}.00</li>
            <li class="list-group-item">Languages: ${order.languages}</li>
            <li class="list-group-item">Tools: ${order.tools}</li>    
        </ul>`;
        document.getElementById('modal-info').insertAdjacentHTML('beforeend', modalHTML);
        modal.style.display = 'block';
        modal.classList.add('show');
    }

    closeModal = () => {
        document.getElementById('modal-info').innerHTML = '';
        modal.style.display = 'none';
        modal.classList.remove('show');
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            closeModal();
        }
    }
    processTemplatesData = (data) => {       
        templateData = data;
    }

    // Process template data to pre fill the order form
    processTemplate = (template) => {
        let languagesArr = document.querySelectorAll('.languages');
        let toolsArr = document.querySelectorAll('.tools');
        name.value = template
        for( const name of templateData) {
            if (template === name.name) {
                
                for (const language of name.languages) {    
                    for (const input of languagesArr) {
                        if (input.value === language) {
                            if (input.checked === false) {
                                input.click();
                            }
                        }
                    }               
                }
                for (const tool of name.tools) {    
                    for (const input of toolsArr) {
                        if (input.value === tool) {
                            if (input.checked === false) {
                                input.click();
                            }
                        }
                    }               
                }
            }
        }
    };
    clearTally = () => {
        ordertally.innerHTML = '';
    }
    clearCheck = (className) => {
        let inputs = document.querySelectorAll(`.${className}`);
        for (const input of inputs) {
            input.checked = false;
        }
    }
});



