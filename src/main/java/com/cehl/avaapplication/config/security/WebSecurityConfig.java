package com.cehl.avaapplication.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception{
        return http.csrf(t -> t.disable()).authorizeHttpRequests(auth ->{auth.anyRequest().permitAll();}).build();
    }
    
}
