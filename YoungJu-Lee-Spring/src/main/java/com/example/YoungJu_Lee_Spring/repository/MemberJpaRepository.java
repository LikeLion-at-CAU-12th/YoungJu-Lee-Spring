package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    //List<Member> findByName(String username);
    Member findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Member> findByEmail(String email);
}
