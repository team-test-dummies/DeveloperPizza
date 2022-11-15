package com.revature.controller;

import com.revature.dao.StartOrderDao;
import io.javalin.http.Context;

import java.sql.SQLException;

public class StartOrderController {

    public static void startOrder(Context context) {
        String item = context.pathParam("item");
        try {
            context.json(StartOrderDao.listPopulate(item));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
