package com.revature.controller;

import com.revature.dao.StartOrderDao;
import com.revature.data.records.Authority;
import io.javalin.http.Context;

import java.sql.SQLException;


public class StartOrderController {

    public static void startOrder(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        if (authority == null) {
            context.status(401);
        } else {
            context.json(StartOrderDao.startOrderList());
        }
    }
}
