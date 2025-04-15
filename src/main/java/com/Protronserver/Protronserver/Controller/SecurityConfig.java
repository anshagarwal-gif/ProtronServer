package com.Protronserver.Protronserver.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors() // Enable CORS
                .and()
                .authorizeHttpRequests()
                .anyRequest().permitAll(); // Bypass auth for everything

        return http.build();
    }

}
