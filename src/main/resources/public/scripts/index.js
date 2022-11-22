// assuming we are logged in, queries whoami to store id and moves to homepage
function setIdAndHomepage() {
    fetch(
        "/whoami",
        {
            method: "GET",
            credentials: "include"
        }
    ).then(response => {
        // remember sessionStorage converts values to strings
        if (response.status == 200) {
            return response.json();
        }
    }).then(data => {
        sessionStorage.setItem("id", data.id);
        location.href = '/pages/userprofile.html';
    })
}

// I like to make sure that the content is loaded.
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
                setIdAndHomepage();
            } else if (res.status === 401) {
                tempMessage("Invalid username or password");
            } else if (res.status === 403) {
                tempMessage("Account is not active");
            } else if (res.status === 400) {
                tempMessage("Fields cannot be empty");
            }
        }).catch (err => {
            console.log(err);
        })
    });

    function tempMessage(message) {
        error.innerHTML = message;
        error.style.opacity = '1';
        setTimeout(function() {
            error.style.opacity =   "0";
        }, 3000);
    }

});