package com.fali.electronic.store.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
public class User {

	@Id
	private String userId;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_email",unique = true)
	private String email;
	@Column(length = 10)
	private String password;
	private String gender;
	@Column(length = 1000)
	private String about;
	
	@Column(name = "user_image_name")
	private String imageName;
}
