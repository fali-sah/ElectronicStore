package com.fali.electronic.store.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fali.electronic.store.validate.ImageNameValid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	
	private String userId;
	
	@Size(min = 3,max=20,message = "invalid name !!")
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9_.+\\-]+[\\x40][a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$",message="invalid email")
	@NotBlank(message="email is required")
	private String email;
	
	@NotBlank(message="password is required")
	private String password;
	
	@Size(min=4,max=6,message="invalid gender")
	private String gender;
	
	@NotBlank(message="write something about yourself")
	private String about;
	
	//@Pattern
	//custom validator
	
	@ImageNameValid
	private String imageName;

}
