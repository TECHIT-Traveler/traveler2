package com.ll.traveler.domain.member.member.repository;

import com.ll.traveler.domain.member.member.entity.Member;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    Boolean existsByUsername(String username);
    Member findByUsername(String username);
}

