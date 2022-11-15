package com.revature.service;

import com.revature.dao.OrderDao;
import com.revature.exception.OrderNotFoundException;
import com.revature.exception.OrderUnsuccessfullyAddedException;
import com.revature.model.Order;

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
    public static Order createOrder(Order addOrder) throws SQLException {
        addOrder.setSkillset(addOrder.getSkillset());
        addOrder.setLocation(addOrder.getLocation());
        addOrder.setAvailability(addOrder.getAvailability());
        addOrder.setSalary(addOrder.getSalary());
        addOrder.setExperience(addOrder.getExperience());
        addOrder.setEducation(addOrder.getEducation());
        addOrder.setCertifications(addOrder.getCertifications());
        addOrder.setLanguages(addOrder.getLanguages());
        addOrder.setFrameworks(addOrder.getFrameworks());
        addOrder.setDatabases( addOrder.getDatabases());
        addOrder.setOperatingsystems( addOrder.getOperatingsystems());
        addOrder.setTools( addOrder.getTools());
        addOrder.setOrderID(addOrder.getOrderID());

        int ordersAdded = OrderDao.createOrder(addOrder);

        if (ordersAdded != 1) {
            throw new OrderUnsuccessfullyAddedException("Order was not created");
        }
        return addOrder;
    }

    // EDIT
    public static void editOrder(Order editOrder) throws SQLException, IOException {
        int ordersEdited = OrderDao.editOrder(editOrder);

        if (ordersEdited != 1) {
            throw new OrderNotFoundException("Order was not edited");
        }
    }

    // DELETE
    public static boolean deleteOrder(int deleteOrder) throws SQLException, IOException {
        int ordersDeleted = OrderDao.deleteOrder(deleteOrder);

        if (ordersDeleted != 1) {
            throw new OrderNotFoundException("Order was not deleted");
        }
        return false;
    }
}
