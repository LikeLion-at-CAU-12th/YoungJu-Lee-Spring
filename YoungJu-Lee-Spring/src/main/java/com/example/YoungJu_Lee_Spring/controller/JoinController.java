package com.example.YoungJu_Lee_Spring.controller;

import com.example.YoungJu_Lee_Spring.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.YoungJu_Lee_Spring.dto.request.JoinRequest;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JoinController {

    private final MemberService memberService;

        @PostMapping("/join")
        public void join(@RequestBody JoinRequest joinRequest){
            memberService.join(joinRequest);
        }

}
