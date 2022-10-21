package com.crudapp.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "users")
public class User {
	
	@SequenceGenerator(name = "users_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private Long id;
	
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String password;
	
	public User(String nome, String email, String password) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
