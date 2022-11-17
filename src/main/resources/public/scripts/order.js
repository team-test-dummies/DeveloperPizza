
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
            <input class="form-check-input" type="checkbox" value="" id="topping-id"> 
            <label class="form-check-label" for="topping-id">${data.language}
            </label>
            </div>`
        }).join("");
//        Adds the element after the last child of the element selected
        toppings.insertAdjacentHTML("beforeend",html);
    }
    const processTools = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <input class="form-check-input" type="checkbox" value="" id="topping-id"> 
            <label class="form-check-label" for="topping-id">${data.tool}
            </label>
            </div>`
        }).join("");
//        Adds the element after the last child of the element selected
        tools.insertAdjacentHTML("beforeend",html);
    }
    const processPremades = (data) => {
        const html = data.map(data => {
            return `<div class="form-check-inline">
            <input class="form-check-input" type="checkbox" value="" id="topping-id">
            <label class="form-check-label" for="topping-id">${data.premade}
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


    function addListenerInputs() {    
        const inputs = document.querySelectorAll('input');
        for (const input of inputs) {
        input.addEventListener('click', (event) => {
            const elem = event.currentTarget;
            if (elem.checked) {
            const label = elem.parentNode.textContent;
            console.log(label);
            const tally = `<li class="list-group-item">${label}</li>`;
            ordertally.insertAdjacentHTML("beforeend",tally);
            }
        });
    }}
    signout.addEventListener('click', () => {
        logout();
    });


});

const logout = () => {
    fetch(`/logout/`, {
        method: `POST`,
    }).then((res) => {
        if (!res.ok) {
            throw Error("Error", res.status);
        }
        window.location.href = '../index.html';
});
}


