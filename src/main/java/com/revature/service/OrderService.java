package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.DataNotFoundException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    public static Authority authenticate(Context context) throws UnauthorizedException {
        Authority authority = context.sessionAttribute("authority");
        if (authority == null) throw new UnauthorizedException();
        return authority;
    }
    public static void authorize(int userId, int orderId) throws SQLException, ForbiddenException {
        int foundUserId = OrderDao.getOrder(orderId).userId();
        if (foundUserId != userId) throw new ForbiddenException();
    }
}
