package com.university.management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
// NEW IMPORT: Required to allow GET requests for logout
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 1. Public Pages
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()

                        // 2. TEACHER DASHBOARD (The new section you just built)
                        .requestMatchers("/teacher/dashboard").hasRole("TEACHER")

                        // 3. ADMIN ONLY Pages
                        .requestMatchers("/students/**", "/student/**").hasRole("ADMIN")
                        .requestMatchers("/teachers/**", "/teacher/new", "/teacher/save", "/teacher/edit/**", "/teacher/delete/**").hasRole("ADMIN")
                        .requestMatchers("/modules/**", "/module/**").hasRole("ADMIN")

                        // 4. Schedule Logic
                        .requestMatchers(HttpMethod.GET, "/sessions").authenticated()
                        .requestMatchers("/session/**").hasRole("ADMIN")

                        // 5. Evaluation Management
                        .requestMatchers("/evaluation/new", "/evaluation/save").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/evaluations").authenticated()

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        // Success Handler: Redirects users based on their role
                        .successHandler((request, response, authentication) -> {
                            var roles = authentication.getAuthorities();
                            for (var role : roles) {
                                if (role.getAuthority().equals("TEACHER")) { // Check for "TEACHER" or "ROLE_TEACHER"
                                    response.sendRedirect("/teacher/dashboard");
                                    return;
                                } else if (role.getAuthority().equals("ROLE_TEACHER")) {
                                    response.sendRedirect("/teacher/dashboard");
                                    return;
                                } else if (role.getAuthority().equals("ADMIN") || role.getAuthority().equals("ROLE_ADMIN")) {
                                    response.sendRedirect("/");
                                    return;
                                }
                            }
                            response.sendRedirect("/");
                        })
                        .permitAll()
                )
                .logout((logout) -> logout
                        // FIX: This allows the simple <a> link to trigger logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/403"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}