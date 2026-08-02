package com.medibook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for the MediBook application.
 * 
 * <p>Configures HTTP security rules, permits public access to REST APIs, H2 Console,
 * and Swagger OpenAPI documentation during test and development execution.</p>
 * 
 * @author MediBook Team
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the SecurityFilterChain bean enabling unauthenticated access for development APIs and H2 console.
     * 
     * @param http The HttpSecurity builder instance to configure. Must not be null.
     * @return The configured SecurityFilterChain instance.
     * @throws Exception If an error occurs during HTTP security configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
