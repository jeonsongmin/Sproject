package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, Long> {

    /*
    * 회원 sql 작성란
    * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
    * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
    * */

    @Query(value = "select * from tb_member where tb_member.mb_email = :mb_email", nativeQuery = true)
    public MemberDTO findByMb_Email(String mb_email);
}
