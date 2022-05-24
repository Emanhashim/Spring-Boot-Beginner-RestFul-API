package com.example.May3.UserLogin;

import com.example.May3.Domain.Roles;

public class LoginResponse {
    private String jwt;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Roles roles;
    private String country;
    private String gender;
    private String message;

    public LoginResponse(Long id, String username, Roles roles, String country, String gender, String jwt) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.country = country;
        this.gender = gender;
    }

    public LoginResponse(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
