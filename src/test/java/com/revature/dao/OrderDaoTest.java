package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.records.Order;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // just a spot check to be replaced, so intentionally fails
    @Test(enabled = false)
    public void getOrdersTesty() throws SQLException {
        Assert.assertEquals(OrderDao.getOrders(9), null);
    }

    @Test(enabled = true)
    public void postOrderTest() throws SQLException {
            OrderDao.postOrder(
                1,
                    new Order(
                            0,
                            false,
                            List.of("Java", "SQL"),
                            List.of("Windows", "Linux")
                    )
            );
            System.out.println(OrderDao.getOrders(1));
    }


}
