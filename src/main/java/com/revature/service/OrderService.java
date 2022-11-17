package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.data.records.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public static List<Order> getOrders(int user_id) throws SQLException {
        return OrderDao.getOrders(user_id);
    }
}
