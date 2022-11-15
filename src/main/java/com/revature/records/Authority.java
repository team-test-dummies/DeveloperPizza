package com.revature.records;


import com.revature.enums.Role;

public final record Authority(
    Integer id,
    Role role
) {}
