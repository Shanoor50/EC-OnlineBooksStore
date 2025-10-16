package com.nt.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {
	
	

	private String firstName;
	private String lastName;
	
	@NotBlank(message = "email cannot blank")
	@Schema(description = "email",example = "enter the email")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "password cannot blank")
	@Schema(description = "password",example = "enter the password")
	@Column(name = "password")
	private String password;
	
	private long contactId;
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

}
