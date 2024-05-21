package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.AnswerDTO;
import com.smhrd.blurbla.model.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDTO, Long> {

    /*
     * 문의사항 답변 [관리자용] sql 작성란
     * (예시....)
     * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
     * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
     * */


    @Query(value = "SELECT *\n" +
                    "FROM tb_payment\n" +
                    "WHERE mb_email = :mb_email", nativeQuery = true)
    List<PaymentDTO> selectMember(String mb_email);


}



