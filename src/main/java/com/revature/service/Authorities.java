package com.revature.service;

import com.revature.data.exception.UnauthorizedException;
import com.revature.data.records.Authority;
import io.javalin.http.Context;

public class Authorities {
    public static Authority getAuthority(Context context) throws UnauthorizedException {
        Authority authority = context.sessionAttribute("authority");
        if (authority == null) throw new UnauthorizedException();
        return authority;
    }
}
