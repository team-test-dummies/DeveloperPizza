const baseUrl = 'https://780179b2-4424-41cb-83e5-73eec012969f.mock.pstmn.io';

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
            window.location.href = `${baseUrl}/order`;
        }
        });
});