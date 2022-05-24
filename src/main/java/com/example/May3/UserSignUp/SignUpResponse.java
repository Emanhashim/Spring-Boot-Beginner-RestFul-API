package com.example.May3.UserSignUp;

import com.example.May3.Domain.Roles;

public class SignUpResponse {

       private String message;
        private String firstname;
        private String lastname;
        private String username;
        private Roles roles;
        private String country;
        private String gender;
        private String password;
        private String confirmPassword;
        private String birthday;

        public SignUpResponse(String message) {
            this.message = message;
        }

        public SignUpResponse(String username,
                              Roles roles,
                              String country,
                              String gender,
                              String message,
                              String firstname,
                              String lastname,
                              String password,
                              String confirmPassword,
                              String birthday) {
            this.username = username;
            this.roles = roles;
            this.country = country;
            this.gender = gender;
            this.message = message;
            this.firstname =firstname;
            this.lastname = lastname;
            this.password = password;
            this.confirmPassword = confirmPassword;
            this.birthday = birthday;
        }



        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
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



