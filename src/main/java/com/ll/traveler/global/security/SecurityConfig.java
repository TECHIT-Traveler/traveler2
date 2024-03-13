package com.ll.traveler.global.security;

import com.ll.traveler.global.security.jwt.JWTFilter;
import com.ll.traveler.global.security.jwt.JWTUtil;
import com.ll.traveler.global.security.jwt.LoginFilter;
import com.ll.traveler.global.security.oAuth.CustomSuccessHandler;
import com.ll.traveler.global.security.oAuth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // BCryptPasswordEncoder Bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain Bean 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                // CORS 설정
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//                    CorsConfiguration configuration = new CorsConfiguration();
//                    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8090"));
//                    configuration.setAllowedMethods(Collections.singletonList("*"));
//                    configuration.setAllowCredentials(true);
//                    configuration.setAllowedHeaders(Collections.singletonList("*"));
//                    configuration.setMaxAge(3600L);
//                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                    return configuration;
//                }))
//                 .headers((headers) -> headers
//                                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
//                                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                // CSRF disable
                .csrf(csrf -> csrf.disable())

                // 경로별 인가 작업
                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers("/member/**").authenticated()
                        .requestMatchers("/adm/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                // Form 로그인 설정
                .formLogin( formLogin  -> formLogin
                        .loginPage("/member/login")
                        //                       .loginProcessingUrl("member/login")
                        .defaultSuccessUrl("/")
                        .successHandler(customSuccessHandler)
                        .permitAll())
                // 로그아웃 관련 설정 추가
                .logout(logout -> logout
                        .logoutUrl("/member/logout")
                        .logoutSuccessUrl("/")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "Authorization")// 삭제할 쿠키 목록
                        .addLogoutHandler((request, response, authentication) -> {
                            // JWT 토큰 삭제
                            jwtUtil.invalidateJwt(request, response);
                        }))
                //LoginFilter 추가
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class)
                //JWTFilter 추가
                .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class)
                // 소셜로그인 설정
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(principalOauth2UserService))
                        .successHandler(customSuccessHandler)
                );
        // 세션 설정
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션을 사용하지 않고 각 요청을 독립적으로 처리하도록 함

        return http.build();
    }
}