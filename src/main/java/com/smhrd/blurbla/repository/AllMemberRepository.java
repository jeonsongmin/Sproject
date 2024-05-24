package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.AllMemberDTO;
import com.smhrd.blurbla.model.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AllMemberRepository extends JpaRepository<AllMemberDTO, Long> {

    /*
    * 회원 sql 작성란
    * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
    * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
    * */

    // 회원 모든 정보 조회
    @Query(value = "SELECT p.mb_email, m.mb_pw, m.mb_role, m.joined_at, p.payed_at " +
            "FROM tb_payment p JOIN tb_member m ON p.mb_email = m.mb_email " +
            "UNION " +
            "SELECT mb_email, mb_pw, mb_role, joined_at, null as payed_at " +
            "FROM tb_member " +
            "WHERE mb_email NOT IN (SELECT p.mb_email FROM tb_payment p JOIN tb_member m ON p.mb_email = m.mb_email)", nativeQuery = true)
    List<AllMemberDTO> memberSelectAll();
}
