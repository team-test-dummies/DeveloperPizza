package com.revature.controller;

import com.revature.data.records.Template;
import com.revature.service.TemplatesService;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

public class TemplatesController {

    public static void getTemplates(Context context) throws SQLException {
        // queryParams should be pulled into an object here
        // we can check if the user is logged in here
        // call the service function that retrieves our data
        List<Template> templates = TemplatesService.getTemplates();
        context.json(templates);
    }

    public static void getLanguages(Context context) throws SQLException {
        List<String> languages = TemplatesService.getLanguages();
        context.json(languages);
    }

    public static void getTools(Context context) throws SQLException {
        List<String> tools = TemplatesService.getTools();
        context.json(tools);
    }

    public static void getSoftSkills(Context context) {
        /* /soft-skills */
        throw new Error("unimplemented");
    }

    public static void getServices(Context context) {
        /* /services */
        throw new Error("unimplemented");
    }
}
