 package com.merin.newboot.security;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.core.Ordered;
 import org.springframework.core.annotation.Order;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;

 @Configuration
 @EnableWebSecurity
 @Order(Ordered.HIGHEST_PRECEDENCE)
 public class SecurityConfig {

     @SuppressWarnings("deprecation")
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
             .csrf().disable()
             .authorizeRequests(authorizeRequests ->
                 authorizeRequests
                     .requestMatchers("/auth/signup", "/auth/signin", "/api/sales", "/api/sales/**").permitAll()
                     .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll() // Allow access to Swagger
                     .anyRequest().authenticated()
             )
             .httpBasic(); 
         return http.build();
     }

     // Configure AuthenticationProviders...
     @Bean
     public BCryptPasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 }
