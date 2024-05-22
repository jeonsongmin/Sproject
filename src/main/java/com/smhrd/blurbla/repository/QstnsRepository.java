package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.QstnsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
 * 문의사항 sql 작성란
 * (예시....)
 * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
 * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
 * */

@Repository
public interface QstnsRepository extends JpaRepository<QstnsDTO, Long> {

// [문의사항] 모든 문의 SELECT
    @Query(value = "SELECT *" +
                    "FROM tb_qstns", nativeQuery = true)
    List<QstnsDTO> qstnsSelectList();

    // [문의사항] 금일 문의생성 카운트
    @Query(value = "SELECT COUNT(*) \n" +
                    "FROM tb_qstns\n" +
                    "WHERE DATE(questioned_at) = CURDATE();", nativeQuery = true)
    public int createQsntsCount();

    // [문의사항] 전체 문의 답변대기
    @Query(value = "SELECT Count(*)\n" +
                    "FROM tb_qstns\n" +
                    "WHERE qstn_answer = 'N';", nativeQuery = true)
    public int standQsntsCount();

    // [문의사항] 전체 문의 답변완료
    @Query(value = "SELECT Count(*)\n" +
                    "FROM tb_qstns\n" +
                    "WHERE qstn_answer = 'Y';", nativeQuery = true)
    public int answerQsntsCount();


    // [문의사항] 답변 여부를 Y로 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_qstns \n" +
                    "SET qstn_answer = 'Y'\n" +
                    "WHERE qstn_idx = :qstn_idx", nativeQuery = true)
    public void qsntnsUpdate(String qstn_idx);


    @Query(value = "SELECT * FROM tb_qstns", nativeQuery = true)
    List<QstnsDTO> findQnaList();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_qstns \n" +
                    "WHERE qstn_idx = :qstn_idx", nativeQuery = true)
    void qsntsDelete(String qstn_idx);


}



