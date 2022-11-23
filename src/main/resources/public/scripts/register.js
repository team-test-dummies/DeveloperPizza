
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
    const registerErr = document.getElementById('registerErr');

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
            window.location.href = '../index.html';
            alert('Log in to your new account')
        }  else if (accountName.length == 0) {
            errorMessage("Full name required.");
        } else if (username.length == 0 || username.length < 6 || username.length > 16) {
            errorMessage('Username must be 6-16 characters long');
        }
        else if (password.length == 0 || password.length < 6 || password.length > 16) {
            errorMessage("Password should be 6-16 characters long");
        }
        else if (phoneNumber.length == 0 ) {
            errorMessage("Valid phone number required");
        }
        else if (email.length == 0) {
            errorMessage("Valid email required.");
        }
        else if (location.length == 0) {
            errorMessage("Location required.");
        }
        else {
            alert('Registration unsuccessful')
        }
        
            function errorMessage(message) {
                registerErr.innerHTML = message;
                registerErr.style.opacity = '1';
                setTimeout(function() {
                   registerErr.style.opacity =   "0";
                }, 3000);
            }

            function validate() {
                usernameRegex = null;
                passwordRegex = null;
                phoneRegex = /^[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$/;
                emailRegex = null;

                var userResult = usernameRegex.test(username);
                var passResult = passwordRegex.test(password);
                var phoneResult = phoneRegex.test(phoneNumber);

                return false;
            }
    })
    
    
    
    
    /*.then((responseBody) => {
            const message = responseBody.message;

            const p1 = document.createElement('p');
            p1.innerHTML = `${message}`;

            const registerErrDiv = document.getElementById('registerErr');
            registerErrDiv.appendChild(p1);
            
            setTimeout(() => {
                p1.style.display = 'none';
                }, 3000);
    })*/
});