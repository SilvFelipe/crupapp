package com.crudapp.crud.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crudapp.crud.dto.UserDto;
import com.crudapp.crud.model.User;
import com.crudapp.crud.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> founded = repository.findByEmail(username);
		founded.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválidos"));
		return founded.map(UserDto::new).get();
	}
	
}
