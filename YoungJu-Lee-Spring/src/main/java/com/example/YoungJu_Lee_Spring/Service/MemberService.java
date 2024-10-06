//package com.example.YoungJu_Lee_Spring.Service;
//
//import com.example.YoungJu_Lee_Spring.domain.Member;
//import com.example.YoungJu_Lee_Spring.repository.MemberJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//    private final MemberJpaRepository memberJpaRepository;
//
//    public Page<Member> getMembersByPage(int page, int size){
//        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
//        return memberJpaRepository.findAll(pageable);
//
//    }
//
//    public Page<Member> getMembersByAgeGreaterthan20(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return memberJpaRepository.findByAgeGreaterThanEqualOrderByUsernameAsc(20, pageable);
//    }
//
//    public Page<Member> getMembersByUsernameStartsWith(int page, int size, String username){
//        Pageable pageable = PageRequest.of(page, size);
//        return memberJpaRepository.findByUsernameStartsWith(username, pageable);
//    }
//
//    public void printMembersByPage(int page, int size) {
//        Page<Member> memberPage = getMembersByPage(page, size);
//        List<Member> members = memberPage.getContent();
//
//        for (Member member : members) {
//            System.out.println("ID: " + member.getId() + ", Username: " + member.getUsername());
//        }
//    }
//
//    public void printMember(Member member) {
//        System.out.println("ID: " + member.getId() + ", Username: " + member.getUsername() + ", Age: " + member.getAge());
//    }
//
//}
