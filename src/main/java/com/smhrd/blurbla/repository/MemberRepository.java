package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**/


    @Query(value = "select * from tb_member where tb_member.mb_email = :mb_email", nativeQuery = true)
    public Member findByMb_Email(String mb_email);
}
