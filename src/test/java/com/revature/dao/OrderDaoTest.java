package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.enums.Education;
import com.revature.data.records.Order;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OrderDaoTest {

    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // getAllOrders()

    // getOrders(int user_id)
    @DataProvider(name = "user ids and order totals")
    private static Iterator<Object[]> userIdsAndOrderTotals() {
        // compare to create.sql file in features
        List<Object[]> hardcode = List.of(
                new Integer[] {1, 0},
                new Integer[] {8, 3},
                new Integer[] {9, 2}
        );
        return hardcode.iterator();
    }

    @Test(dataProvider = "user ids and order totals")
    public static void getOrdersCountTest(int userID, int totalOrders) throws SQLException {
        int actual = OrderDao.getOrders(userID).size();
        Assert.assertEquals(actual, totalOrders);
    }

    // postOrder(int user_id, Order pending)
    @Test(dataProvider = "user ids and order totals", dependsOnMethods = "getOrdersCountTest")
    public static void postOrderCountTest(int userId, int originalAmountOfOrders) throws SQLException {
        Order sample = new Order(
                0,
                "intern",
                Education.NONE,
                400,
                false,
                Set.of("SQL", "Java"),
                Set.of("Windows", "Linux"),
                userId
        );
        OrderDao.postOrder(sample);
        int actual = OrderDao.getOrders(userId).size();
        Assert.assertEquals(actual, originalAmountOfOrders + 1);
    }

    // getOrder(int order_id)
    @Test
    public static void getOrderTest() throws SQLException {
        Order expected = new Order(
                    1,
                "science person",
                Education.BACHELORS,
                40000,
                false,
                Set.of("Java", "CSS", "HTML"),
                Set.of("IntelliJ", "Visual Studio Code", "Selenium"),
                8
        );
        Order actual = OrderDao.getOrder(expected.id());
        Assert.assertEquals(actual, expected);
    }

    // putOrder(int user_id, Order pending)
    // this qualifies as spagoti code
    @Test(dataProvider = "user ids and order totals", dependsOnMethods = "getOrdersCountTest")
    public static void putOrderTest(int userId, int orderTotal) throws SQLException {
        List<Order> orders = OrderDao.getOrders(userId);
        Order sample = new Order(
                0,
                "intern",
                Education.NONE,
                400,
                true,
                Set.of("SQL", "Java"),
                Set.of("Windows", "Linux"),
                userId
        );
        for (Order order : orders) {
            OrderDao.putOrder(sample);
        }
        throw new SkipException("unimplemented");
    }


    // deleteOrder(int orderID)
    @Test(dataProvider = "user ids and order totals", dependsOnMethods = "getOrdersCountTest")
    public static void deleteOrderTest(int userId, int orderTotal) throws SQLException {
        List<Order> orders = OrderDao.getOrders(userId);
        for (Order order : orders) {
            int orderId = order.id();
            OrderDao.deleteOrder(order);
        }
        orders = OrderDao.getOrders(userId);
        Assert.assertTrue(orders.size() == 0);
    }


}
