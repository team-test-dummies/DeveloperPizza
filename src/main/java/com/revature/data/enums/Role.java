package com.revature.data.enums;

import io.javalin.security.RouteRole;

public enum Role implements RouteRole {
    ADMIN,
    STAFF,
    CUSTOMER,
    ANYONE
}
