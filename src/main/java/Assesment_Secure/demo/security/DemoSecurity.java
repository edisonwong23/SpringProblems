package Assesment_Secure.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())   // Disable CSRF protection (for APIs or dev)
                .headers(headers -> headers.frameOptions().disable()) // Optional: for H2 console
                .formLogin(login -> login.disable()) // Optional: disable login form
                .httpBasic(basic -> basic.disable()); // Optional: disable basic auth

        return http.build();
    }
}
