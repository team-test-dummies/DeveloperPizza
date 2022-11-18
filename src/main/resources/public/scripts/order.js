
document.addEventListener('DOMContentLoaded', () => {
    const premade = document.getElementById('premade');
    const toppings = document.getElementById('toppings');
    const tools = document.getElementById('tools');
    const ordertally = document.getElementById('ordertally');
    const salary = document.getElementById('salary');
    const signout = document.getElementById('signout');
    //
    salary.addEventListener('input', function() {
        this.value = this.value.replace(/(\.\d\d)\d+|([\d.]*)[^\d.]/, '$1$2');
    });
    const processLanguages = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <label class="form-check-label">${data.language}
            <input class="form-check-input languages" name="${data.language}" type="checkbox"> 
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
            <input class="form-check-input tools" name="${data.tool}" type="checkbox"> 
            </label>
            </div>`
        }).join("");
//        Adds the element after the last child of the element selected
        tools.insertAdjacentHTML("beforeend",html);
    }
    const processPremades = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <label class="form-check-label">${data.premade}
            <input class="form-check-input premade" name="${data.premade}" type="checkbox">
            </label>
            </div>`
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
    addListenerInputs();
    function addListenerInputs() {    
        const inputs = document.querySelectorAll('input');
        for (const input of inputs) {
        input.addEventListener('click', (event) => {
            const elem = event.currentTarget;
            if (elem.checked) {
                let label = elem.parentNode.textContent;
                console.log(label);
                let tally = `<li id="${label}" class="list-group-item">${label}</li>`;
                ordertally.insertAdjacentHTML("beforeend",tally);
            } else if (elem.checked === false) {
                let label = elem.parentNode.textContent;
                let removeLi = document.getElementById(label);
                removeLi.remove();
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
    
});



