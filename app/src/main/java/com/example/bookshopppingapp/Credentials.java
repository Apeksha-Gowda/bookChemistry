package com.example.bookshopppingapp;

public class Credentials {

    String name;
    String username;
    String password;
    String role;

    public Credentials() {
    }

    public Credentials(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
