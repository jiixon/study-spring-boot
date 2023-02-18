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
//.authorizeRequests() : 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미
//.mvcMatchers("") : ""(특정경로)로 지정해서 권한 설정 가능
//.permitAll() : 앞의 것들로 설정한 리소스의 접근을 인증절차 없이 허용한다
//.anyRequest() : 모든 리소스를 의미(이후는 접근 허용 수준 결정)
//.hasRole("USER") : 사용자가 주어진 역할이 있다면(Role_User), 접근허용
//
//formLogin() : 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용하겠다
//.loginPage("/index") : 사용자가 따로만든 로그인 페이지 사용시 설정
//.loginProcessingUrl("/login") : 인증처리(로그인)를 하는 URL 설정
//.defaultSuccessUrl("/") : 인증 성공했을 경우 이동하는 페이지 설정
//
//.oauth2Login()
//.loginPage("/loginForm")
//.defaultSuccessUrl("/")
//.failureUrl("/loginForm") : 인증 실패했을 경우 이동하는 페이지 설정
//.userInfoEndpoint(principalOauth2UserService): 로그인후 후처리
//return httpSecurity.build()

//mvcMatchers(): mvc 패턴에 대해 접근설정
//("/index") 이 부분에 모든 사용자 접근가능
//("/css/**"):이 부분도 설정해줘야함 설정해주지 않으면 css같은 정적 리소스 파일 전부에도 모든 사용자가 접근 가능하게 코드를 바꿔야