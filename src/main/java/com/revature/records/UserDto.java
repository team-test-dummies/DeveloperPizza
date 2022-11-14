package com.revature.records;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final record UserDto(
        Integer id,
        String accountType,
        String accountName,
        String userName,
        String password,
        String phoneNumber,
        String email,
        String location
) {

    public static UserDto parse(ResultSet result) throws SQLException {
        return new UserDto(
            result.getInt("id"),
            result.getString("accountType"),
            result.getString("accountName"),
            result.getString("userName"),
            result.getString("password"),
            result.getString("phoneNumber"),
            result.getString("email"),
            result.getString("location")
        );
    }

    public static List<UserDto> parseAll(ResultSet results) throws SQLException {
        List<UserDto> collector = new ArrayList<>();
        while (results.next()) {
            collector.add(parse(results));
        }
        return Collections.unmodifiableList(collector);
    }

}
