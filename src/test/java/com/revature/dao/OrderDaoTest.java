package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.enums.Education;
import com.revature.data.enums.Role;
import com.revature.data.records.Order;
import com.revature.data.records.User;
import kotlin.Pair;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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
    public static void postOrderCountTest(int userID, int originalAmountOfOrders) throws SQLException {
        Order sample = new Order(
                0,
                "intern",
                Education.NONE,
                400,
                false,
                Set.of("SQL", "Java"),
                Set.of("Windows", "Linux")
        );
        OrderDao.postOrder(userID, sample);
        int actual = OrderDao.getOrders(userID).size();
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
                Set.of("IntelliJ", "Visual Studio Code", "Selenium")
        );
        Order actual = OrderDao.getOrder(expected.id());
        Assert.assertEquals(actual, expected);
    }

    // getUserID(int order_id)

    // putOrder(int user_id, Order pending)

    // deleteOrder(int orderID)

    // just a spot check to be replaced, so intentionally fails
//    @Test
//    public void getOrdersTesty() throws SQLException {
//        Assert.assertEquals(OrderDao.getOrders(9), null);
//    }
//
//    @Test
//    public void postOrderTest() throws SQLException {
//            OrderDao.postOrder(
//                1,
//                    new Order(
//                            0,
//                            "test",
//                            Education.NONE,
//                            1000,
//                            false,
//                            List.of("Java", "SQL"),
//                            List.of("Windows", "Linux")
//                    )
//            );
//            System.out.println(OrderDao.getOrders(1));
//    }


}
