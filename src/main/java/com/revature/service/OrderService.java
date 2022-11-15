package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.data.records.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    // VIEW
    public static List<Order> getAllOrders() throws SQLException, IOException {
        return OrderDao.getAllOrders();
    }

    // FILTER
    public static List<Order> getOrderByOrderID(int filterID) throws SQLException, IOException {
        return OrderDao.filterOrderID(filterID);
    }

    // CREATE

    // EDIT

    // DELETE
}
