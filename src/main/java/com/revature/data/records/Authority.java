package com.revature.data.records;


import com.revature.data.enums.Role;

public final record Authority(
    Integer id,
    Role role
) {}
