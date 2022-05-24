package com.example.May3.UserUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateRequest {

	@ApiModelProperty(value= "This is users Put Name Value that used for registering ")
	private String firstname;

	@ApiModelProperty(value= "This is users Put Father Name Value that used for registering ")
	private String lastname;

	@ApiModelProperty(value= "This is users Put Password That used previously or currently ")
	private String password;

	@ApiModelProperty(value= "This is users Put A Phone Number Value that used for registering ")
	private String username;

	@ApiModelProperty(value= "This is users Put's the new password to be changed ")
	private String newpass;

	@ApiModelProperty(value= "This is users Put Name Value that used for registering ")
	private String resetPasswordToken;
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
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

	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
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

}
