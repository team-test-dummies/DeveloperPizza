package com.revature.controller;

import com.revature.dao.StartOrderDao;
import com.revature.data.records.Authority;
import com.revature.data.records.StartOrderItems;
import io.javalin.http.Context;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;

import java.sql.SQLException;


public class StartOrderController {
    @OpenApi(
            summary = "Endpoint used for the start order page",
            operationId = "start-order",
            path = "/start-order/",
            methods = HttpMethod.GET,
            tags = {"Template"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = StartOrderItems[].class)}),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void startOrder(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        if (authority == null) {
            context.status(401);
        } else {
            context.json(StartOrderDao.startOrderList());
        }
    }
}
