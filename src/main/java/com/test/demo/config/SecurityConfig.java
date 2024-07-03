package com.test.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return httpSecurity.build();
        
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails normalUser = User.builder()
    //         .username("user")
    //         .password("$2a$12$r0v2VbkRPwXdwM3dbfZPaO1TmJl7nsv9oKsYQ6Thk.igp97JgEagK") 
    //         .roles("USER")
    //         .build();

    //     UserDetails adminUser = User.builder()
    //         .username("admin")
    //         .password("$2a$12$MzzcRk2IXpMGZF11p8rzCO6b9DxW37NrDjYdHwaxfxHmvBmeTYbf2") 
    //         .roles("ADMIN", "USER")
    //         .build();

    //     return new InMemoryUserDetailsManager(normalUser, adminUser);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
