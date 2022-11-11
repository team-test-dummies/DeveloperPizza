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
    public List<Order> getAllOrders() throws SQLException {
        return orderDao.getAllOrders();
    }

    // FILTER
    public Order filterOrder(String filterID) throws SQLException, IOException {
        Order order = orderDao.filterOrder(filterID);

        if (order == null) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            return order;
        }
    }
}
