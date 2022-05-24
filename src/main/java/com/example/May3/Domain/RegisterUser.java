package com.example.May3.Domain;

import io.swagger.annotations.Api;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@EqualsAndHashCode

@Table(name = "reg")
@Api(value = "User Database Structure", description = "The Data base and Data Type")


public class RegisterUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String confirmPassword;
    private String username;
    private String email;
    private String birthdate;
    @Enumerated(EnumType.STRING)
    private Roles roles = Roles.USER;
    @Enumerated(EnumType.STRING)
    private Levels levels = Levels.LEVEL_1;
    private Boolean locked = true;
    private Boolean enabled = true;
    private String resetPasswordToken;
    private String country;
    private String gender;
    private String region;
    private String city;
    private String Subcity;
    private String Woreda;
    private String HouseNo;

//this for getters setters methods also constructors

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Levels getLevels() {
        return levels;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSubcity() {
        return Subcity;
    }

    public void setSubcity(String subcity) {
        Subcity = subcity;
    }

    public String getWoreda() {
        return Woreda;
    }

    public void setWoreda(String woreda) {
        Woreda = woreda;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }


//    this for User's signup constructor parameter for signup user
    public RegisterUser(String firstname,
                        String lastname,
                        String password,
                        String username,
                        String birthdate,
                        String email,
                        String gender,
                        String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

//this is a defasult constructor for user login
    public RegisterUser() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.name());
    return Collections.singletonList(authority);
}

    public Collection<? extends GrantedAuthority> getAuthorities2() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(levels.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }
public String  getConfirmPassword(){
        return confirmPassword;
}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
