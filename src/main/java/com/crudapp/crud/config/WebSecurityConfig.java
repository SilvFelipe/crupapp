package com.crudapp.crud.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.crudapp.crud.filter.JwtRequestFilter;
import com.crudapp.crud.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Autowired 
	JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	/*
	 * @BEAN 
	 * DEFINE QUE ESTÁ FUNÇÃO SERÁ CHAMADA SEMPRE QUE SEU NOME FOR UTILIZADO NO CÓDIGO
	 * PODEMOS DEFINIR ISSO COM AUTOWIRED, POR EXEMPLO
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCRYPT É UMA DAS BIBLIOTECAS NATIVAS DO JDK PARA CRIPTOGRAFIA
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager auhenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests().antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling()
			// STATELESS NÃO FAZ ARMAZENAMENTO EM MEMÓRIA
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/*
	 * 			CROSS ORIGIN RESOURCE ... (CORS)
	 * PONTO DE ORIGEM DE CHAMADA É DIFERENTE DO END POINT DO BACK END
	 * É PRECISO CONFIGURAR PARA PERMITIR ESSE TIPO DE CHAMADA
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
}
