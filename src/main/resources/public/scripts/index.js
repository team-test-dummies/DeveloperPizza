
document.addEventListener('DOMContentLoaded', () => {   

    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const loginButton = document.getElementById('login');
    const error = document.getElementById('error');

    loginButton.addEventListener('click', () => {
        fetch(`/login/`, {
            method: 'POST',
            body: `{"username":"${usernameInput.value}","password":"${passwordInput.value}"}`,
            credentials: 'include' // Very important so that the browser will retain the Cookie when we log in
            // by default the browser will throw the Cookie away
        }).then((res) => {
            if (res.status === 204) {
                window.location.href = '/pages/startorder.html';

            } else {
                alert('Invalid username or password');
            }
        });
    });

});