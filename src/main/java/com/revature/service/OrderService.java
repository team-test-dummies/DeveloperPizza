package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.DataNotFoundException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public static void authorize(int userId, int orderId) throws SQLException, ForbiddenException {
        int foundUserId = OrderDao.getOrder(orderId).userId();
        if (foundUserId != userId) throw new ForbiddenException();
    }
}
