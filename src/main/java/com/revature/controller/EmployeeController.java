package com.revature.controllers;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import io.javalin.Javalin;

import java.util.List;

public class EmployeeController implements Controller {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void mapEndpoint(Javalin app) {
        app.get("/employees", ctx -> {
            List<Employee> allEmployees =  employeeService.getAllEmployees();
            ctx.json(allEmployees);
        });
    }
}
