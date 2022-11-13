package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.exception.OrderNotFoundException;
import com.revature.model.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    // VIEW
    public List<Order> getAllOrders() throws SQLException, IOException {
        return orderDao.getAllOrders();
    }

    // FILTER
    public List<Order> getOrderByOrderID(int filterID) throws SQLException, IOException {
        return orderDao.filterOrderID(filterID);
    }

    // CREATE

    // EDIT

    // DELETE
}
