//package com.crudapp.crud.config;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class CorsConfig extends WebSecurityConfigurerAdapter {
//
//
//
//   private static final String[] PUBLIC_MATCHERS = { "/**" };
//   
//   
//   protected void config(AuthenticationManagerBuilder auth) throws Exception {       
//       auth.inMemoryAuthentication().withUser("admin")
//           .password("admin")
//           .roles("ADMIN") 
//           .and().withUser("user") 
//           .password("user")
//           .roles("USER");
//   }
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	authorization(http);
//        http.cors().and().csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
//    }
//    
//    protected void authorization(HttpSecurity http) throws Exception {
//    	http.authorizeRequests() 
//    	.mvcMatchers(HttpMethod.DELETE, "/tutorials").hasRole("ADMIN")
////        .antMatchers().hasRole("ADMIN") 
//        .and().formLogin(); 
//    }
//
//
//   @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//    
//}
