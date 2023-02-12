package com.example.dekoracje.config;

import com.example.dekoracje.model.entity.UserRole;
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

//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/",
//                                "/login/**", "/registration/**", "/images/**", "/scripts/**").permitAll()
////                                .requestMatchers("/**").permitAll()
//                        .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/",
//                                "/login/**", "/registration/**", "/images/**", "/scripts/**",
//                                "/**").hasRole("ADMIN")
//                        .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/",
//                                "/login/**", "/registration/**", "/images/**", "/scripts/**",
//                                "/**", "/customer/**", "/document" ).hasRole("CUSTOMER")
//                        .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/",
//                                "/login/**", "/registration/**", "/images/**", "/scripts/**",
//                                "/customer/**").hasRole("SUPPLIER")
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        //TODO: problem z rolą, obojętnie co by sie nie zrobiło, to nie widzi tej roli

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/contact/**", "/about/**", "/error/**", "/index",
                                "/login/**", "/registration/**", "/images/**", "/scripts/**",
                                "/address", "/customer", "/document", "/cart", "/product", "/stock", "/supplier").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
