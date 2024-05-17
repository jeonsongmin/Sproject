package com.smhrd.blurbla.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 *   해당 DTO는 문의 답변 (관리자) 정보가 담긴 테이블
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_answer")
public class AnswerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ans_idx;
    // 문의사항 번호
    private String qstn_idx;
    private String ans_content;
    private String ans_file;
    private Date answered_at;
    private String admin_id;
}
