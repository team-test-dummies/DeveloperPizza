package com.revature.controller;

import com.revature.data.records.Order;
import com.revature.data.records.Template;
import com.revature.service.TemplatesService;
import io.javalin.http.Context;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;

import java.sql.SQLException;
import java.util.List;

public class TemplatesController {

    @OpenApi(
            summary = "Get all the job templates in the database",
            operationId = "getTemplates",
            path = "/templates",
            methods = HttpMethod.GET,
            tags = {"Template"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Template[].class)}),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getTemplates(Context context) throws SQLException {
        // queryParams should be pulled into an object here
        // we can check if the user is logged in here
        // call the service function that retrieves our data
        List<Template> templates = TemplatesService.getTemplates();
        context.json(templates);
    }

    @OpenApi(
            summary = "Get all the computer languages in the database",
            operationId = "getLanguages",
            path = "/languages",
            methods = HttpMethod.GET,
            tags = {"Template"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = String[].class)}),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getLanguages(Context context) throws SQLException {
        List<String> languages = TemplatesService.getLanguages();
        context.json(languages);
    }

    @OpenApi(
            summary = "Get all the computer tools in the database",
            operationId = "getTools",
            path = "/tools",
            methods = HttpMethod.GET,
            tags = {"Template"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = String[].class)}),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getTools(Context context) throws SQLException {
        List<String> tools = TemplatesService.getTools();
        context.json(tools);
    }
// Might as well comment out unless we want to leave the API call up and we can test for code coverage
//    public static void getSoftSkills(Context context) {
//        /* /soft-skills */
//        throw new Error("unimplemented");
//    }
//
//    public static void getServices(Context context) {
//        /* /services */
//        throw new Error("unimplemented");
//    }
}
