package com.example.securitydemo2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // Spring Security Filter가 Spring Filter Chain에 등록됨
public class SecurityConfig {


}
