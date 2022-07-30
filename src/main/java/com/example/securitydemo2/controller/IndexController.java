package com.example.securitydemo2.controller;

import com.example.securitydemo2.model.Member;
import com.example.securitydemo2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public @ResponseBody
    String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    @PostMapping("/join")
    public @ResponseBody String join(Member member) {
        member.setRole("ROLE_USER"); //처음에는 USER라는 권한 부여
        member.setPassword(passwordEncoder.encode(member.getPassword())); //password 인코딩 해서 저장 (안하면 로그인 시 에러 발생!)

        memberRepository.save(member);
        return "joined!";
    }
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "info";
    }

    @GetMapping("/data")
    public @ResponseBody String data() {
        return "data";
    }

}
