package com.revature.dao;

import com.revature.records.StartOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StartOrderDao extends Dao {

    // Rewriting to accept parameters for what to populate
    public static List<StartOrder> listPopulate(String item) throws SQLException {
        try(Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ?");
            ps.setString(1, item);
            ResultSet result = ps.executeQuery();
            List<StartOrder> topping= new ArrayList<>();
            while (result.next()) {
                topping.add(new StartOrder(
                        result.getString("topping"))
                );
            }
            return topping;
        }
    }

}
