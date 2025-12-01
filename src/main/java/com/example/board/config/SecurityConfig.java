package com.example.board.config;

import com.example.board.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod; 

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // CORS preflight 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 1) 메인/로그인/회원가입 화면은 모두 허용
                        .requestMatchers(
                                "/",              // 메인
                                "/login",         // 로그인 페이지
                                "/signup",        // 회원가입 페이지 (네가 쓰는 URL에 맞게 수정)
                                "/users/signup",  // 예: /users/signup 이런 형태라면
                                "/users/register",
                                "/css/**", "/js/**", "/images/**" // 정적 리소스
                        ).permitAll()

                        // 2) 회원가입/로그인 API는 누구나 접근 가능
                        .requestMatchers("/api/users/**").permitAll()

                        // 3) 게시글 API는 전부 인증 필요 (사실 anyRequest().authenticated()에 포함되지만 명시해도 됨)
                        // .requestMatchers("/api/posts/**").authenticated()

                        // 4) 나머지 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "https://board-frontend-sable.vercel.app",
                "http://15.164.61.176:8080"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}