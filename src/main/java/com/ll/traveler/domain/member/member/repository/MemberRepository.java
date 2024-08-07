package com.ll.traveler.domain.member.member.repository;

import com.ll.traveler.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
    //username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성

    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    Optional<Member> findByProviderAndProviderId(String provider, String providerId);

    Boolean existsByUsername(String username);

    Optional<Member> findByEmail(String email);

    Member findById(Member author);

    Member findByNickname(String nickname);
}

