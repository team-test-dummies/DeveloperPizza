package com.revature.records;

public final record UserDto(
        Integer id,
        String accountType,
        String accountName,
        String userName,
        String password,
        String phoneNumber,
        String email,
        String location
) {}
