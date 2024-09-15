package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember(){
        // 멤버 생성
        Member member1 = Member.builder()
                .name("member1")
                .email("ehung1@likelion")
                .build();

        Long savedMember1 = memberRepository.save(member1);
        System.out.println(savedMember1);

        Member member2 = Member.builder()
                .name("member2")
                .email("ehung2@likelion")
                .build();

        Long savedMember2 = memberRepository.save(member2);
        System.out.println(savedMember2);
    }

}