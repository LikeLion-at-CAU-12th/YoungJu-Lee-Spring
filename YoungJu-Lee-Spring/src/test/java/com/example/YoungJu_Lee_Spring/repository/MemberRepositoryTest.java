package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() {
        Member member = Member.builder()
                .username("memberA")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    @Rollback()
    public void testFindByUsername() {
        Member member = Member.builder()
                .username("memberA")
                .age(23)
                .email("polarpheno@gmail.com")
                .build();

        Long savedId = memberRepository.save(member);

        em.clear();

        List<Member> byUsername = memberRepository.findByUsername(member.getUsername());

        for (Member member1 : byUsername) {
            System.out.println(member1.getId());
        }
    }
}