package com.example.YoungJu_Lee_Spring.Service;

import com.example.YoungJu_Lee_Spring.domain.Member;
import com.example.YoungJu_Lee_Spring.dto.request.JoinRequest;
import com.example.YoungJu_Lee_Spring.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 인코더 DI

    public void join(JoinRequest joinRequest) {
        if (memberJpaRepository.existsByUsername(joinRequest.getUsername())) {
            return; // 나중에는 예외 처리
        }

        Member member = Member.builder()
                .username(joinRequest.getUsername())
                .email(joinRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))
                .build();

      memberJpaRepository.save(member);
    }
}
