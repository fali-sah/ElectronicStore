package com.fali.electronic.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fali.electronic.store.dtos.ApiResponseMessage;
import com.fali.electronic.store.dtos.UserDto;
import com.fali.electronic.store.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	//create
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
		UserDto userDto1 = userService.createUser(userDto);
		
		return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") String userId){
		UserDto updateUser = userService.updateUser(userDto, userId);
	
		
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	
	
	//delete
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
		
		userService.deleteUser(userId);
		
		ApiResponseMessage message = ApiResponseMessage.builder().message("user is deleted successfull !!").success(true).status(HttpStatus.OK).build();
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	//getall
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
	//get single
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getUser( @PathVariable String userId){
		
		return new ResponseEntity<UserDto>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	//getbyemail
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto>getUserByEmail( @PathVariable String email){
		
		return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
	}
	
	//search user
	

	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<UserDto>> searchUser( @PathVariable String keywords){
		
		List<UserDto> searchUser = userService.searchUser(keywords);
		return new ResponseEntity<>(searchUser,HttpStatus.OK);
	}
	
	
}
