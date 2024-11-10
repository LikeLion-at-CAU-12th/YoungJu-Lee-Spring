package com.example.YoungJu_Lee_Spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.example.YoungJu_Lee_Spring.Service.CustomUserDetailsService;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((SecurityConfig::corsAllow))
            .csrf(AbstractHttpConfigurer::disable)  //일단 실습에서는 disable 처리
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/join", "/login").permitAll()   //위에서부터 탐색
                        .requestMatchers("/api/**").authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder();}

    private static void corsAllow(CorsConfigurer<HttpSecurity> corsCustomizer){
        corsCustomizer.configurationSource(request->{
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L);

            return configuration;
        });
    }

    private final CustomUserDetailsService customUserDetailsService; // UserDetailsService DI
}
