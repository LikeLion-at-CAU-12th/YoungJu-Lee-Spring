package com.example.YoungJu_Lee_Spring.Service;

import com.example.YoungJu_Lee_Spring.domain.Member;
import com.example.YoungJu_Lee_Spring.repository.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private final Random random = new Random();

    @BeforeEach
    public void setUp() {
        IntStream.range(0,100).forEach(i -> {
            String username = "user" + random.nextInt(10000);
            String email = "user" + random.nextInt(10000) + "@example.com";
            int age = random.nextInt(60 - 18 + 1) + 18;

            Member member = Member.builder()
                    .username(username)
                    .email(email)
                    .age(age)
                    .build();
            memberJpaRepository.save(member);
        });
    }

    @Test
    public void testPrintMemberByPage(){
        memberService.printMembersByPage(5,10);
    }

    @Test
    public void testFindByAgeGreaterThanOrderByUsernameAsc(){
        memberService.getMembersByAgeGreaterthan20(3, 10)
                .getContent()
                .forEach(member -> {
                    memberService.printMember(member);
                }
                );
    }

    @Test
    public void testFindByUsernameStartsWith(){

        Member joojoo = Member.builder()
                .username("joojoo")
                .email("joo@joo.com")
                .age(24)
                .build();
        memberJpaRepository.save(joojoo);

        memberService.getMembersByUsernameStartsWith(0,1, "joo")
                .getContent()
                .forEach(member -> {
                    memberService.printMember(member);
                }
                );
    }
}