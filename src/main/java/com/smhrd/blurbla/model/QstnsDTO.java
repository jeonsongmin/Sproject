package com.smhrd.blurbla.model;


/*
 *   해당 DTO는 문의사항에 대한 테이블
 * */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_qstns")
public class QstnsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qstn_idx;           // 시퀀스 번호
    private String qstn_title;      // 제목
    private String qstn_content;    // 내용
    private Date questioned_at;     // 생성일자
    private String qstn_open;       // 문의 공개 여부
    private String qstn_category;       // 문의 분류
    private String qstn_answer;       // 문의 답변 여부
    private String mb_email;        // 문의 작성자
}
