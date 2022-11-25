
const accountHidden = document.getElementById('accounttype');
const fullNameInput = document.getElementById('fullname');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phonenumber');
const locationInput = document.getElementById('location');
const signupButton = document.getElementById('signup');
const registerErr = document.getElementById('registerErr');

signupButton.addEventListener('click', () => {

    const accountType = accountHidden.value;
    const accountName = fullNameInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const phoneNumber = phoneNumberInput.value;
    const email = emailInput.value;
    const location = locationInput.value;

    const usernameRegex = /^[a-zA-Z0-9]+$/;
    const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$/;
    const phoneRegex = /^[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$/;
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\.{1}[a-zA-Z]{2,3}$/;

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
        if (res.status === 201) {
            var c = confirm('Registration successful!\nLogin to your new account');
            if (c) {
                window.location.href = '../index.html';
            } else {}
        } else if (accountName.length == 0) {
            errorMessage("Full name is required");
        } else if (username.length == 0 || username.length < 6 || username.length > 16
            || username.match(usernameRegex) == null) {
            errorMessage("Username must be 6-16 characters long\nCannot include special characters (@, $, !, *, etc)");
        } else if (password.length == 0 || password.length < 6 || password.length > 16
            || password.match(passwordRegex) == null) {
            errorMessage("Password must be 6-16 characters long\nMust contain atleast one upppercase letter and one number");
        } else if (phoneNumber.length == 0 || phoneNumber.match(phoneRegex) == null) {
            errorMessage("A valid phone number is required");
        } else if (email.match(emailRegex) == null) {
            errorMessage("A valid email is required");
        } else if (location.length == 0) {
            errorMessage("Location is required");
        } else {
            alert('Registration unsuccessful');
        }
        
        
        function errorMessage(message) {
            registerErr.innerHTML = message;
            registerErr.style.opacity = '1';
            setTimeout(function() {
               registerErr.style.opacity = "0";}, 
               4000);
        }
    })
});