package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.QstnsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * 문의사항 sql 작성란
 * (예시....)
 * - 쿼리문 작성할시 nativeQuery = true 추가할 것.
 * - where 문은 tb_member(테이블명).mb_email(컬럼명) = :(추가문법)mb_email(컬럼명)
 * */

@Repository
public interface QstnsRepository extends JpaRepository<QstnsDTO, Long> {
    @Query(value = "SELECT COUNT(*) \n" +
                    "FROM tb_qstns\n" +
                    "WHERE DATE(questioned_at) = CURDATE();", nativeQuery = true)
    public int createQsntsCount();

    @Query(value = "SELECT Count(*)\n" +
                    "FROM tb_qstns\n" +
                    "WHERE qstn_open = 'N';", nativeQuery = true)
    public int standQsntsCount();

    @Query(value = "SELECT Count(*)\n" +
                    "FROM tb_qstns\n" +
                    "WHERE qstn_open = 'Y';", nativeQuery = true)
    public int answerQsntsCount();
}



