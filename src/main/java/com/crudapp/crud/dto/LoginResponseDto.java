package com.crudapp.crud.dto;

public class LoginResponseDto {

	private String nome;
	private String email;
	private String token;
	
	public LoginResponseDto() {
		super();
	}
	
	public LoginResponseDto(String nome, String email, String token) {
		super();
		this.nome = nome;
		this.email = email;
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginResponseDto [nome=" + nome + ", email=" + email + ", token=" + token + "]";
	}

}
