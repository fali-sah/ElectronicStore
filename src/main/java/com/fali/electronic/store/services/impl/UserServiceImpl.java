package com.fali.electronic.store.services.impl;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fali.electronic.store.dtos.UserDto;
import com.fali.electronic.store.entities.User;
import com.fali.electronic.store.exceptions.ResourseNotFoundException;
import com.fali.electronic.store.repositories.UserRepository;
import com.fali.electronic.store.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDto createUser(UserDto userDto) {

		String userId = UUID.randomUUID().toString();

		userDto.setUserId(userId);

		// dto->entity
		User user = dtoToEntity(userDto);

		User savedUser = userRepository.save(user);

		// entity->dto

		UserDto newDto = entityToDto(savedUser);
		return newDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("user not found with given id"));
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		user.setGender(userDto.getGender());
		user.setImageName(userDto.getImageName()); 
		// email update.
		// save data
		User updatedUser = userRepository.save(user);

		UserDto updatedDto = entityToDto(updatedUser);
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("user not found by given id"));
		// delete user
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> allUser = userRepository.findAll();

		List<UserDto> dtoList = allUser.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("user not found by given id"));

		return entityToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourseNotFoundException("user not found by given email id"));

		return entityToDto(user);
	}

	@Override
	public List<UserDto> searchUser(String keyword) {
		List<User> users = userRepository.findByNameContaining(keyword);

		List<UserDto> userDtoList = users.stream().map(user ->entityToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	private UserDto entityToDto(User savedUser) {
//
//		UserDto userDto = UserDto.builder().userId(savedUser.getUserId()).name(savedUser.getName())
//				.about(savedUser.getAbout()).email(savedUser.getEmail()).gender(savedUser.getGender())
//				.password(savedUser.getPassword()).imageName(savedUser.getImageName()).build();

		return mapper.map(savedUser, UserDto.class);
	}

	private User dtoToEntity(UserDto userDto) {

//		User user = User.builder().userId(userDto.getUserId()).name(userDto.getName()).email(userDto.getEmail())
//				.password(userDto.getPassword()).about(userDto.getAbout()).gender(userDto.getGender())
//				.imageName(userDto.getImageName()).build();
//		
		return mapper.map(userDto,User.class);
	}
}
