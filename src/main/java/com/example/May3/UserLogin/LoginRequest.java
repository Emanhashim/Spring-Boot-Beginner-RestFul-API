package com.example.May3.UserLogin;

import io.swagger.annotations.ApiModelProperty;

public class LoginRequest {

    @ApiModelProperty(value= "This is users Phone Number ")
    private String username;

    @ApiModelProperty(value= "This is users Password! ")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
