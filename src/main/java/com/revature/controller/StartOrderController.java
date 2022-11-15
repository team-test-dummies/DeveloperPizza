package com.revature.controller;

import com.revature.dao.StartOrderDao;
import io.javalin.http.Context;

import java.sql.SQLException;

public class StartOrderController {

    public static void startOrder(Context context) {
        try {
            context.json(StartOrderDao.listLanguages());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
