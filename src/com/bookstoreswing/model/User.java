package com.bookstoreswing.model;

/**
 * Simple user/customer model
 */
public class User {
    private String username;
    private String name;
    private String address;

    public User(String username, String name, String address) {
        this.username = username; this.name = name; this.address = address;
    }

    public String getUsername(){ return username; }
    public String getName(){ return name; }
    public String getAddress(){ return address; }
}
