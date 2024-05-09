package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.Board;
import com.smhrd.blurbla.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
}
