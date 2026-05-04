package com.grocery.app.config;

import com.grocery.app.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // ✅ IMPORTANT

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                        .requestMatchers("/cart/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/payment/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/delivery/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/tracking/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
        /*.requestMatchers(HttpMethod.GET, "/products").hasAnyRole("USER", "ADMIN")*/
        return http.build();
    }
}
