package com.revature.dto;

import java.util.Objects;

public class LoginInfo {
    private String username;
    private String password;

    public LoginInfo() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInfo loginInfo = (LoginInfo) o;
        return Objects.equals(username, loginInfo.username) && Objects.equals(password, loginInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
