package com.revature.data.records;


import com.revature.data.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final record User(
        Integer id,
        Role accountType,
        String accountName,
        String userName,
        String password,
        String phoneNumber,
        String email,
        String location
) {

    public static User parse(ResultSet result) throws SQLException {
        return new User(
            result.getInt("id"),
            Role.valueOf(result.getString("accountType")),
            result.getString("accountName"),
            result.getString("userName"),
            result.getString("password"),
            result.getString("phoneNumber"),
            result.getString("email"),
            result.getString("location")
        );
    }

    public static List<User> parseAll(ResultSet results) throws SQLException {
        List<User> collector = new ArrayList<>();
        while (results.next()) {
            collector.add(parse(results));
        }
        return Collections.unmodifiableList(collector);
    }

}
