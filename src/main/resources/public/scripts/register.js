

const accountDropdown = document.getElementById('accounttype');
const fullNameInput = document.getElementById('fullname');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phonenumber');
const locationInput = document.getElementById('location');
const signupButton = document.getElementById('signup');

signupButton.addEventListener('click', () => {
    const accountName = fullNameInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const phoneNumber = phoneNumberInput.value;
    const email = emailInput.value;
    const location = locationInput.value;
    const accountType = "CUSTOMER";
    fetch(`/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                accountType,
                accountName,
                username,
                password,
                phoneNumber,
                email,
                location
            })
    }).then((res) => { 
            if (res.status === 201) {
            window.location.href = '../index.html';
            alert('Log in to your new account')
        } else {
            alert('Registration unsuccessful')
        }   
    });
});