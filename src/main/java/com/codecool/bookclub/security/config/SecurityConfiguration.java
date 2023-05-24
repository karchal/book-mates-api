package com.codecool.bookclub.security.config;

import com.codecool.bookclub.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                    .requestMatchers("/api/authentication/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/books/**", "/api/events/**", "/api/topics/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()
                    .requestMatchers("/api/users/profile/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/books/**", "/api/events/**", "/api/topics/**", "/api/comments/**").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/events/**", "/api/topics/**", "/api/comments/**").hasAnyRole("MODERATOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/events/**", "/api/topics/**").hasAnyRole("MODERATOR", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutUrl("/api/logout")
                    .addLogoutHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                    .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
