package com.codecool.bookclub.config;

import com.codecool.bookclub.jwt.JwtAuthenticationFilter;
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

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider; // indicates a class can process a specific Authentication implementation.

    // a bean responsible for configuring all the HTTP-security of the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Enables cross-site request forgery protection
                .csrf()
                // we disable CSRF protection for now
                .disable()
                // white list - requests that don't need to be authenticated
                .authorizeHttpRequests()
                .requestMatchers("/api/authentication/**")
                .permitAll()
                // any other request should be authenticated
                .anyRequest()
                .authenticated()
                // we don't want to store the authentication/session state, the session should be stateless
                // we want authenticate any subsequent request that require authentication
                // we need to configure the session management
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Spring Security will never create an HttpSession and it will never use it to obtain the SecurityContext
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
