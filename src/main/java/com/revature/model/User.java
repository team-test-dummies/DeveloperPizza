//package com.revature.model;
//
//import java.util.Objects;
//
//public class User {
//    private int id;
//<<<<<<< HEAD
//    private String username;
//    private String password;
//    private String role;
//=======
//    private String accountType;
//    private String accountName;
//    private String username;
//    private String password;
//    private String phoneNumber;
//    private String email;
//    private String location;
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//
//    public User() {
//    }
//
//<<<<<<< HEAD
//    public User(int id, String username, String password, String role) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//=======
//    public User(int id, String accountType, String accountName, String username, String password, String phoneNumber, String email, String location) {
//        this.id = id;
//        this.accountType = accountType;
//        this.accountName = accountName;
//        this.username = username;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.location = location;
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//<<<<<<< HEAD
//=======
//    public String getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(String accountType) {
//        this.accountType = accountType;
//    }
//
//    public String getAccountName() {
//        return accountName;
//    }
//
//    public void setAccountName(String accountName) {
//        this.accountName = accountName;
//    }
//
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//<<<<<<< HEAD
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//=======
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//<<<<<<< HEAD
//        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
//=======
//        return id == user.id && Objects.equals(accountType, user.accountType) && Objects.equals(accountName, user.accountName) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(location, user.location);
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//    }
//
//    @Override
//    public int hashCode() {
//<<<<<<< HEAD
//        return Objects.hash(id, username, password, role);
//=======
//        return Objects.hash(id, accountType, accountName, username, password, phoneNumber, email, location);
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//<<<<<<< HEAD
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
//=======
//                ", accountType='" + accountType + '\'' +
//                ", accountName='" + accountName + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", email='" + email + '\'' +
//                ", location='" + location + '\'' +
//>>>>>>> 008ff5f6dbd0580fb9694b2445acf2879c490e23
//                '}';
//    }
//}
