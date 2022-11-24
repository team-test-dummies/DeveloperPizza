document.addEventListener('DOMContentLoaded', () => {
    const accountInfoAppend = document.getElementById('accountInfo');
    const orderList = document.getElementById('orders');
    const signout = document.getElementById('signout');

    const processData = (data) => {
        const html =
            `<li class="list-group-item">${data.username}</li>
             <li class="list-group-item">${data.accountName}</li>
             <li class="list-group-item">${data.phoneNumber}</li>
             <li class="list-group-item">${data.email}</li>
             <li class="list-group-item">${data.location}</li>
             <button class="btn btn-primary w-45" id="edit-profile" value="${data.id}">Edit Profile</button>
             <button class="btn btn-primary w-45" id="delete-profile" value="${data.id}">Delete Profile</button>`
        
    //        Adds the element after the last child of the element selected
        accountInfoAppend.insertAdjacentHTML("beforeend",html);
    };

    const processOrders = (data) => {
        const html = data.map(data => {
            return `<li class="list-group-item">
                        <ul class="list-group">
                            <li class="list-group-item">Order ID: ${data.id}</li>
                            <li class="list-group-item">Name: ${data.name}</li>
                            <li class="list-group-item">Language(s): ${data.languages}</li>
                            <li class="list-group-item">Tools: ${data.tools}</li>
                            <li class="list-group-item">Educational Level: ${data.educationRequirement}</li>
                            <li class="list-group-item">Salary: ${data.salary}</li>
                            <li class="list-group-item">
                            <button class="btn btn-primary w-45" id="edit-order" value="${data.id}">Edit Order</button>
                            <button class="btn btn-primary w-45" id="delete-order" value="${data.id}">Delete Order</button>
                            </li>
                        </ul>
                    </li>`
        }).join("");
    //        Adds the element after the last child of the element selected
        orderList.insertAdjacentHTML("beforeend",html);
    };

    fetch(`/user/`, {
        method: `GET`,
        credentials: `include`
    }).then((res) => {
        if (!res.ok) {
            if (res.status === 401) {
                window.location.href = '../index.html';
            } else if(!response.ok) {
                throw Error("Error", response.status);
            }
        
        }
        return res.json();
    }).then((data) => {
        processData(data);
        return fetch(`/orders/`, {
            method: `GET`}).then((res) => {
        if (!res.ok) {
            throw Error("Error", res.status);
        }
        return res.json();
    }).then((orderData) => {
        processOrders(orderData);
    })
    })

    signout.addEventListener('click', () => {
        fetch(`/logout/`, {
            method: `POST`,
            credentials: `include`
        }).then((res) => {
            if (!res.ok) {
                throw Error("Error", res.status);
            } else {
                window.location.href = '../index.html';
            }
        });
    });
});