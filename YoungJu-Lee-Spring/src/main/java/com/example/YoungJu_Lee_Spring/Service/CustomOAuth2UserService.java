package com.example.YoungJu_Lee_Spring.Service;

import com.example.YoungJu_Lee_Spring.domain.Member;
import com.example.YoungJu_Lee_Spring.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberJpaRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String accessToken = userRequest.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken); // 토큰 출력 (확인용)

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");


        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> Member.builder()
                        .username(username)
                        .email(email)
                        .password("") // OAuth 사용자는 비밀번호가 필요하지 않으므로 빈 문자열로 처리
                        .build());

        // 새로운 사용자는 DB에 저장
        if (member.getId() == null) {
            memberRepository.save(member);
        } else{
            // 이미 존재하는 이메일이라면 username 업데이트
          member.updateUsername(username);
          memberRepository.save(member);
        }


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(),
                "sub");
    }
}
