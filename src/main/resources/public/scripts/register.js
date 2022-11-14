const baseUrl = 'http://localhost:8080';

const accountDropdown = document.getElementById('accounttype');
const fullNameInput = document.getElementById('fullname');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phonenumber');
const locationInput = document.getElementById('location');
const signupButton = document.getElementById('signup');

signupButton.addEventListener('click', () => {
    const accountType = accountDropdown.value;
    const accountName = fullNameInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const phoneNumber = phoneNumberInput.value;
    const email = emailInput.value;
    const location = locationInput.value;
    
    fetch(`${baseUrl}/register`, {
        method: 'POST',
            body: JSON.parse(`{
                "accountType":"${accountDropdown.value}",
                "accountName":"${fullNameInput.value}",
                "username":"${usernameInput.value}",
                "password":"${passwordInput.value}",
                "phonenumber":"${phoneNumberInput.value}",
                "email":"${emailInput.value}",
                "location":"${locationInput.value}"
            }`)
    }).then((res) => { 
            if (res.status === 200) {
            window.location.href = 'index.html';
            alert('Log in to your new account')
        } else {
            alert('Registration unsuccessful')
        }   
    });

});