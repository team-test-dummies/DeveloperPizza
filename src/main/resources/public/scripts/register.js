

const accountHidden = document.getElementById('accounttype');
const fullNameInput = document.getElementById('fullname');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const emailInput = document.getElementById('email');
const phoneNumberInput = document.getElementById('phonenumber');
const locationInput = document.getElementById('location');
const signupButton = document.getElementById('signup');

signupButton.addEventListener('click', () => {
<<<<<<< HEAD
    const accountType = accountHidden.value;
=======
>>>>>>> a37d9057314dcdcfe5f44aba68ea0869f2fb1ab6
    const accountName = fullNameInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value;
    const phoneNumber = phoneNumberInput.value;
    const email = emailInput.value;
    const location = locationInput.value;
<<<<<<< HEAD
    
    fetch(`/users/`, {
=======
    const accountType = "CUSTOMER";
    fetch(`/users`, {
>>>>>>> a37d9057314dcdcfe5f44aba68ea0869f2fb1ab6
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            },
            body:`{"accountType":"${accountType}, "accountName":"${accountName}",
            "username":"${username}","password":"${password}", "phoneNumber":"${phoneNumber}",
            "email":"${email}", "location":"${location}"}`,
    }).then((res) => { 
            if (res.status === 201) {
            window.location.href = '../index.html';
            alert('Log in to your new account')
        } else {
            alert('Registration unsuccessful')
        }   
    });
});