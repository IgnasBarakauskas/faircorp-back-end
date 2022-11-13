package com.emse.spring.faircorp.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public UserDetailsService userDetailsService() {
        // We create a password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
        );
        manager.createUser(
                User.withUsername("admin").password(encoder.encode("admin")).roles(ROLE_ADMIN).build()
        );
        return manager;
    }

    // Let everyone access this api
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/login")
                .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    // Secure api/** (all apis under /api) so that only logged-in user with any role could access it
    @Bean
    @Order(2)
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/api/**")
                .authorizeRequests(authorize -> authorize.anyRequest().hasAnyRole("ADMIN", "USER"))
                .formLogin(withDefaults())
                .csrf().disable()
                .httpBasic(withDefaults())
                .build();
    }

    // Secure /api/users so that only admin could access it
    @Bean
    @Order(3)
    public SecurityFilterChain filterChain3(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/api/users/**")
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole("ADMIN"))
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

}