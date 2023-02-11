package com.example.dekoracje.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests()
//                .requestMatchers("/**")     // sprawdzić czy to działa bo coś gównem zawiewa
//                .hasRole("ADMIN")                   // to też
                .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/",
                        "/login/**", "/registration/**", "/images/**", "/scripts/**").permitAll()
                .requestMatchers("/**").hasRole("ADMIN")
                .requestMatchers("TODO KURWA").hasRole("CUSTOMER")
                .requestMatchers("TODO KURWA").hasRole("SUPPLIER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
