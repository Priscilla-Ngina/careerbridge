package com.careerBridge.careerBridge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            CustomUserDetailsService customUserDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(customUserDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/users",
                                        "/auth/login"
                                ).permitAll()

                                .requestMatchers(HttpMethod.POST, "/companies")
                                .hasAnyRole("COMPANY")

                                .requestMatchers(HttpMethod.PUT, "/companies/**")
                                .hasAnyRole("COMPANY")

                                .requestMatchers(HttpMethod.DELETE, "/companies/**")
                                .hasAnyRole("COMPANY", "ADMIN")

                                .requestMatchers(HttpMethod.GET, "/companies", "/companies/**")
                                .hasAnyRole("STUDENT", "COMPANY", "ADMIN")



                                .requestMatchers(HttpMethod.POST, "/students")
                                .hasAnyRole("STUDENT")

                                .requestMatchers(HttpMethod.PUT, "/students/**")
                                .hasAnyRole("STUDENT")

                                .requestMatchers(HttpMethod.DELETE, "/students/**")
                                .hasAnyRole("STUDENT", "ADMIN")

                                .requestMatchers(HttpMethod.GET, "/students", "/students/**")
                                .hasAnyRole("STUDENT", "COMPANY", "ADMIN")



                                .requestMatchers(HttpMethod.POST, "/internships")
                                .hasAnyRole("COMPANY")

                                .requestMatchers(HttpMethod.PUT, "/internships/**")
                                .hasAnyRole("COMPANY")

                                .requestMatchers(HttpMethod.DELETE, "/internships/**")
                                .hasAnyRole("COMPANY", "ADMIN")

                                .requestMatchers(HttpMethod.GET, "/internships", "/internships/**")
                                .permitAll()



                                .requestMatchers(HttpMethod.POST, "/applications")
                                .hasAnyRole("STUDENT")

                                .requestMatchers(HttpMethod.PUT, "/applications/**")
                                .hasAnyRole("STUDENT", "COMPANY")

                                .requestMatchers(HttpMethod.DELETE, "/applications/**")
                                .hasAnyRole("STUDENT", "ADMIN")

                                .requestMatchers(HttpMethod.GET, "/applications", "/applications/**")
                                .hasAnyRole("STUDENT", "COMPANY", "ADMIN")


                                .anyRequest().authenticated()
                        )
//                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
