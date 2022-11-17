document.addEventListener('DOMContentLoaded', () => {

    const accountInfoAppend = document.getElementById('accountInfo');
    const orderList = document.getElementById('orders');

    const processData = (data) => {
        const html =
            `<li class="list-group-item">${data.username}</li>
             <li class="list-group-item">${data.accountName}</li>
             <li class="list-group-item">${data.phoneNumber}</li>
             <li class="list-group-item">${data.email}</li>
             <li class="list-group-item">${data.location}</li>`
        
    //        Adds the element after the last child of the element selected
        accountInfoAppend.insertAdjacentHTML("beforeend",html);
    }

    const processOrders = (data) => {
        const html = data.map(data => {
            return `<li class="list-group-item">
                        <ul>
                            <li>Order ID: ${data.orderID}</li>
                            <li>Location: ${data.location}</li>
                            <li>Skillset: ${data.Skillset}</li>
                            <li>Language(s): ${data.orderStatus}</li>
                            <li>Framworks(s): ${data.status}</li>
                            <li>Tools: ${data.tools}</li>
                            <li>OS: ${data.operatingsystems}</li>
                            <li>Experience: ${data.experience}</li>
                            <li>Salary: ${data.salary}</li>
                        </ul>    
                    </li>`
        }).join("");
    //        Adds the element after the last child of the element selected
        orderList.insertAdjacentHTML("beforeend",html);
    }

    // hardcoded username for now
    // ${baseUrl}/profile/
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
});