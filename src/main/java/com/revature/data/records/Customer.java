package com.revature.data.records;

import java.util.Objects;


public class Customer {
    private int id;
    private String accountType;
    private String accountName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String location;

    public Customer() {
    }

    public Customer(int id, String accountType, String accountName, String username, String password, String phoneNumber, String email, String location) {
        this.id = id;
        this.accountType = accountType;
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
    }

    public Customer(int id, String accounttype, String accountname, String username, String phoneNumber, String email, String location) {
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
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(accountType, customer.accountType) && Objects.equals(accountName, customer.accountName) && Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(email, customer.email) && Objects.equals(location, customer.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType, accountName, username, password, phoneNumber, email, location);
    }

    @Override
    public String toString() {
        return "Customer{" +
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

