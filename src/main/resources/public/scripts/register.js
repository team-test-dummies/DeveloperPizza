

const accountHidden = document.getElementById('accounttype');
const fullNameInput = document.getElementById('fullname');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phonenumber');
const locationInput = document.getElementById('location');
const signupButton = document.getElementById('signup');

signupButton.addEventListener('click', () => {

    const accountType = accountHidden.value;
    const accountName = fullNameInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const phoneNumber = phoneNumberInput.value;
    const email = emailInput.value;
    const location = locationInput.value;

    fetch(`/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            },
            body:JSON.stringify({
                accountType,
                accountName,
                username,
                password,
                phoneNumber,
                email,
                location
            })  
    }).then((res) => { 
            return res.json();      
    }).then((responseBody) => {
            const message = responseBody.message;

            const p1 = document.createElement('p');
            p1.innerHTML = `${message}`;

            const registerErrDiv = document.getElementById('registerErr');
            registerErrDiv.appendChild(p1);
            
            setTimeout(() => {
                p1.style.display = 'none';
                }, 3000);
    });
});