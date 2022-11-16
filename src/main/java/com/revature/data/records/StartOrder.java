package com.revature.data.records;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record StartOrder(
        String topping
) {
    public static StartOrder parse(ResultSet result) throws SQLException {
        return new StartOrder(
            result.getString("topping")
        );
    }

    public static List<StartOrder> parseAll(ResultSet results) throws SQLException {
        List<StartOrder> collector = new ArrayList<>();
        while (results.next()) {
            collector.add(parse(results));
        }
        return Collections.unmodifiableList(collector);
    }
}
