package com.crudapp.crud.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.crudapp.crud.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	/*
	 * SECRET KEY É USADA PARA CRIAR CRIPTOGRAFAR E DESCRIPTOGRAFAR O TOKEN
	 * PODE SER QUALQUER COISA
	 * 
	 * -> PARA SISTEMAS GRANDES IDEAL QUE SEJAM GERADOS 
	 * 			CERTIFICADOS PARA CRIAÇÃO DA SECRET KEY <- 
	 */
	private String SECRET_KEY = "crudApp-treinamento";
	
	
	public String extractUsername(final String token) {
		final Claims claims = extractAllClaims(token);
		return (String) claims.get("mail");
	}
	
	private Claims extractAllClaims(final String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public String generateToken(final UserDetails userDetails) {
		final Map<String, Object> claim = new HashMap<>();
		final UserDto user = (UserDto) userDetails;
		//CRIA UM CLAIM COM INFORMAÇÃO DE EMAIL
		claim.put("mail", user.getEmail());
		return createToken(claim, userDetails.getUsername());
	}
	
	/*
	 * CRIA O TOKEN DE ACESSO 
	 * ESSE TOKEN SERÁ UTILIZADO EM TODAS AS REQUESTS FEITAS PELO FRONT END
	 * 
	 * -> PARA SISTEMAS GRANDES IDEAL QUE SEJAM GERADOS 2 TOKENS: 
	 * 		1 TOKEN PARA AUTENTICAÇÃO IMEDIATA
	 * 		1 TOKEN DE REFRESH PARA QUANDO EXPIRAR O PRIMEIRO TOKEN
	 * 		MANTENDO SEMPRE UMA TROCA DE AUTENTICAÇÃO E DIFICULTANDO ATAQUES <-
	 */
	private String createToken(final Map<String, Object> claims, final String subject) {
		// CONSTROE UM JWT JSON DE INFORMAÇÕES
		return Jwts.builder()
				// SETA CAMPO EMAIL E SEU VALOR
				.setClaims(claims)
				// SETA O ASSUNTO
				.setSubject(subject)
				// DEFINE DATA DE ACESSO
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// DEFINE DATA DE EXPIRAÇÃO - 1000 (MS) * 60
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
				// SIGNATURE ALGORITHM HS256 UM DOS MÉTODOS PARA CRIPTOGRAFIA
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validarToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		UserDto user = (UserDto) userDetails;
		return (username.equals(user.getEmail()) &&  !isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	
}
