package com.example.YoungJu_Lee_Spring.controller;

import com.example.YoungJu_Lee_Spring.Service.MemberService;
import com.example.YoungJu_Lee_Spring.config.jwt.JwtTokenProvider;
import com.example.YoungJu_Lee_Spring.domain.Member;
import com.example.YoungJu_Lee_Spring.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.YoungJu_Lee_Spring.dto.request.JoinRequest;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JoinController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

        @PostMapping("/join")
        public void join(@RequestBody JoinRequest joinRequest){
            memberService.join(joinRequest);
        }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody JoinRequest joinRequest) {
        Member member = memberService.login(joinRequest);
        return TokenResponse.of(jwtTokenProvider.generateAccessToken(member.getUsername()), jwtTokenProvider.generateRefreshToken(member.getUsername()));
    }


}
