export {
    login, logout, whoami,
    getOrder, getOrders,
    postOrder, postOrders,
    putOrder, deleteOrder
}

async function login(username, password) {
    const response = await fetch(
        "/login",
        {
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password
            }),
            credentials: "include"
        }
    )
    if (response.ok) {
        sessionStorage.setItem("username", username)
        return;
    }
    else {
        throw new Error("invalid credentials")
    }
}

async function logout() {
    const response = await fetch(
        "/logout",
        {
            method: "POST",
            credentials: "include"
        }
    )
    if (response.ok) {
        sessionStorage.removeItem("id")
        sessionStorage.removeItem("username")
        location = "/"
        return;
    }
    else {
        throw new Error("unable to logout")
    }
}

async function whoami() {
    const response = await fetch(
        "/whoami",
        {
            method: "GET",
            credentials: "include"
        }
    )
    if (response.ok) {
        const description = await response.json()
        sessionStorage.setItem(
            "id",
            description.id
        )
        return description
    }
    else {
        throw new Error("unauthorized user asking who they are");
    }
}

async function getOrder(orderId) {
    const response = await fetch(
        `/orders/${orderId}`,
        {
            method: "GET",
            credentials: "include"
        }
    )
    if (response.ok) {
        return await response.json();
    }
    else {
        throw new Error(`error while getting order ${orderId}`)
    }
}

async function getOrders() {
    const response = await fetch(
        "/orders",
        {
            method: "GET",
            credentials: "include"
        }
    )
    if (response.ok) {
        return await response.json();
    }
    else {
        throw new Error("error while getting orders")
    }
}

async function postOrder(order) {
    const response = await fetch(
        "/orders",
        {
            method: "POST",
            body: JSON.stringify(order),
            credentials: "include"
        }
    )
    if (response.ok) {
        return response.headers.get("location")
    }
    else {
        throw new Error("error while posting order")
    }
}

async function postOrders(...orders) {
    const response = await fetch(
        "/orders",
        {
            method: "POST",
            body: JSON.stringify(orders),
            credentials: "include"
        }
    )
    if (response.ok) {
        return response.headers.get("location")
    }
    else {
        throw new Error(`error while posting orders`)
    }
}

async function putOrder(order) {
    const response = await fetch(
        `/orders/${order.id}`,
        {
            method: "PUT",
            body: JSON.stringify(order),
            credentials: "include"
        }
    )
    if (response.ok) {
        return;
    }
    else {
        throw new Error(`error while put order ${order.id}`)
    }
}

async function deleteOrder(orderId) {
    const response = await fetch(
        `/orders/${orderId}`,
        {
            method: "DELETE",
            credentials: "include"
        }
    )
    if (response.ok) {
        return;
    }
    else {
        throw new Error(`error while delete order ${order.id}`)
    }
}

//        app.get("/user/", UserController::getUser);
//        app.get("/users", UserController::getUsers);
//        app.post("/users", UserController::postUsers);
//        app.put("/users/{username}", UserController::putUser);
//        app.delete("/users/{username}", UserController::deleteUser);