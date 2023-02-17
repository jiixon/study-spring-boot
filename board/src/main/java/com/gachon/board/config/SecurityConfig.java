package com.gachon.board.config;

import com.gachon.board.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면, 미리 인증 및 권한을 체크
public class SecurityConfig {
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

//    @Bean
//    public BCryptPasswordEncoder Encoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().mvcMatchers("/index","/css/**").permitAll()
                .anyRequest().hasRole("USER");
        httpSecurity.formLogin().loginPage("/index").loginProcessingUrl("/login").defaultSuccessUrl("/")
                .and().oauth2Login().loginPage("/loginForm").defaultSuccessUrl("/")
                .failureUrl("/loginForm").userInfoEndpoint().userService(principalOauth2UserService);
        return httpSecurity.build();
    }
}
