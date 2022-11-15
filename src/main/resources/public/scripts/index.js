const baseUrl = 'http://127.0.0.1:8080';

document.addEventListener('DOMContentLoaded', () => {
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const loginButton = document.getElementById('login');
    const error = document.getElementById('error');

    loginButton.addEventListener('click', () => {
        fetch(`${baseUrl}/login`, {
            method: 'POST',
            body: `{"username":"${usernameInput.value}","password":"${passwordInput.value}"}`,
            credentials: 'include' // Very important so that the browser will retain the Cookie when we log in
            // by default the browser will throw the Cookie away
        }).then((res) => {
            if (res.status === 204) {
                window.location.href = '/pages/startorder.html';
            } else {
                error.innerHTML = 'Invalid username or password';
            }
        });
    });
});