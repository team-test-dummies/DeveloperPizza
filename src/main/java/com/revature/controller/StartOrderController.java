package com.revature.controller;

import com.revature.dao.StartOrderDao;
import io.javalin.http.Context;

import java.sql.SQLException;


public class StartOrderController {

    public static void startOrder(Context context) throws SQLException {
        context.json(StartOrderDao.startOrderList());
    }
}
