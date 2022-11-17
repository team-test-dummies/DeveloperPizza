package com.revature.service;

import com.revature.dao.TemplatesDao;
import com.revature.data.records.Template;

import java.sql.SQLException;
import java.util.List;

public class TemplatesService {
    public static List<Template> getTemplates() throws SQLException {
        return TemplatesDao.getTemplates();
    }
}
