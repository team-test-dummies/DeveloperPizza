package com.revature.controller;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
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
