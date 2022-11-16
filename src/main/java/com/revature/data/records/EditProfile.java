package com.revature.data.records;

import java.util.Objects;


public class EditProfile {
    private String accountName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String location;

    public EditProfile() {
    }

    public EditProfile(String accountName, String username, String password, String phoneNumber, String email, String location) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditProfile that = (EditProfile) o;
        return Objects.equals(accountName, that.accountName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, username, password, phoneNumber, email, location);
    }

    @Override
    public String toString() {
        return "EditProfile{" +
                "accountName='" + accountName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
