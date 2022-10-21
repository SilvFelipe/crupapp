package com.crudapp.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudapp.crud.dto.LoginRequestDto;
import com.crudapp.crud.dto.LoginResponseDto;
import com.crudapp.crud.dto.UserDto;
import com.crudapp.crud.service.UserService;
import com.crudapp.crud.util.JwtUtil;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService service;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto){
		/*
		 * COMO SERÁ FEITA A AUTENTICAÇÃO
		 */
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDto.getUsername(), 
						loginDto.getPassword()));
		
		final UserDto user = (UserDto) service.loadUserByUsername(loginDto.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		return new ResponseEntity<LoginResponseDto>(new LoginResponseDto(
				user.getUsername(), 
				user.getEmail(), 
				jwt
				), HttpStatus.ACCEPTED);
	}
	
}
