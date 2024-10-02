package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
    Page<Member> findByAgeGreaterThanEqualOrderByUsernameAsc(int age, Pageable pageable);
    Page<Member> findByUsernameStartsWith(String username, Pageable pageable);
}
