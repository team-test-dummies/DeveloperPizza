const baseUrl = 'http://localhost:8080';


fetch(`${baseUrl}/{username}/profile`, {
    method: `GET`,
    credentials: `include`
}).then((res) => {
    return res.json();
}) .then((responseBody) => {
    const accountType = responseBody.accountType;
    const accountName = responseBody.accountName;
    const username = responseBody.username;
    const password = responseBody.password;
    const phoneNumber = responseBody.phoneNumber;
    const email = responseBody.email;
    const location = responseBody.location;

    const pAccountType = document.createElement('p');
    pAccountType.innerHTML = `Account Type: ${accountType}`;

    const inputAccountName = document.createElement('input');
    inputAccountName.setAttribute('type', 'text');
    inputAccountName.value = `${accountName}`;

    const pUsername = document.createElement('p');
    pUsername.innerHTML = `Username: ${username}`;

    const inputPassword = document.createElement('input');
    inputPassword.setAttribute('type', 'password');
    inputPassword.value = `${password}`;

    const inputPhone = document.createElement('input');
    inputPhone.setAttribute('type', 'text');
    inputPhone.value = `${phoneNumber}`;

    const inputEmail = document.createElement('input');
    inputEmail.setAttribute('type', 'email');
    inputEmail.value = `${email}`;

    const inputLocation = document.createElement('input');
    inputLocation.setAttribute('type', 'text');
    inputLocation.value = `${location}`;
    
})
    