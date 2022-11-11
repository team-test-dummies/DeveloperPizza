const baseUrl = 'http:localhost:8080/login';

const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const loginButton = document.getElementById('login');

loginButton.addEventListener('click', () => {
    const username = usernameInput.value;
    const password = passwordInput.value;
    
    fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify({
        username,
        password,
        }),
    })
        .then((response) => {
            console.log(response.json());
            response.json()
        })
        .then((data) => {
        if (data.message) {
            alert(data.message);
        } else {
            console.log("Login successful");
        }
        });
});