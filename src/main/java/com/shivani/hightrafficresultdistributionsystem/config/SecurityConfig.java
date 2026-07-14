package com.shivani.hightrafficresultdistributionsystem.config;

import com.shivani.hightrafficresultdistributionsystem.auth.filter.JwtFilter;
import com.shivani.hightrafficresultdistributionsystem.ratelimit.filter.RateLimiterFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final RateLimiterFilter rateLimiterFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/results/**").permitAll()

                        //Student APIs
                        .requestMatchers("/api/v1/students/**","/api/v1/results/**").hasRole("STUDENT")

                        //Admin APIs
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

                        //SChool APIs
                        .requestMatchers("/api/v1/school/**").hasRole("SCHOOL")

                        // Everything Else
                        .anyRequest().authenticated()


                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(rateLimiterFilter, JwtFilter.class);


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
