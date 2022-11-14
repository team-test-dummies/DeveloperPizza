package com.revature.model;

import java.util.Objects;

public class Employer {
    private int id;
    private String accountType;
    private String accountName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String location;

    public Employer() {
    }

    public Employer(int id, String accountType, String accountName, String username, String password, String phoneNumber, String email, String location) {
        this.id = id;
        this.accountType = accountType;
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
        Employer employer = (Employer) o;
        return id == employer.id && Objects.equals(accountType, employer.accountType) && Objects.equals(accountName, employer.accountName) && Objects.equals(username, employer.username) && Objects.equals(password, employer.password) && Objects.equals(phoneNumber, employer.phoneNumber) && Objects.equals(email, employer.email) && Objects.equals(location, employer.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType, accountName, username, password, phoneNumber, email, location);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", accountName='" + accountName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

