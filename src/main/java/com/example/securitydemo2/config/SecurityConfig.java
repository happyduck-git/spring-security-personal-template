package com.example.securitydemo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security Filter가 Spring Filter Chain에 등록됨
public class SecurityConfig {

    @Bean
    //기본적으로 Spring Security를 적용하면 모든 request에서 login하는 페이지가 나오게 되는데, authorizeRequests로 제한을 두면 어떤 경우에 권한이 필요한지 설정할 수 있다.
    //대신 원래 나오던 default 로그인 페이지가 더이상 안나옴..!
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //기본 인증된 사용자는 다 접근 가능!
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //ROLE_ADMIN & ROLE_MANAGER인 경우 접근 가능한 경로
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")//ROLE_ADMIN인 경우 접근 가능한 경로
                .anyRequest().permitAll()
                .and() /* .and().formLogin().loginPage("/") 부분은 권한이 없는 페이지에 접속했을 때 login 페이지로 redirect하는 코드*/
                .formLogin()
                .loginPage("/login");

        return http.build();
    }

}
