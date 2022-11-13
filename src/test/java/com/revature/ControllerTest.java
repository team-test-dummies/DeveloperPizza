package com.revature;

import com.revature.controller.OrderController;
import com.revature.dao.ConnectionFactory;
import io.javalin.Javalin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ControllerTest {
    public Connection con;
    public Javalin app;

    @BeforeEach
    public void setpup() throws SQLException, IOException {
        con = ConnectionFactory.getConnection();
        ConnectionFactory.populatedH2Database(con);

        app = Javalin.create();
        OrderController orderController = new OrderController();
        orderController.mapEndpoint(app);
    }

    @AfterEach
    public void clearDB() throws SQLException, IOException {
        ConnectionFactory.clearH2Database(con);
        con.close();
    }
}
