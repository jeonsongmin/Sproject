package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.AnswerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerDTO, Long> {

    /*
     * 문의사항 답변 [관리자용] sql 작성란
     * (예시....)
     * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
     * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
     * */

    
    // [문의 답변] 문의사항의 답변 내역 확인
    @Query(value = "SELECT * FROM tb_answer WHERE qstn_idx = :qstn_idx", nativeQuery = true)
    public List<AnswerDTO> selectQstns_Idx(String qstn_idx);

}



