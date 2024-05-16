package com.smhrd.blurbla.model;


/*
 *   해당 DTO는 문의사항에 대한 테이블
 * */

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_qstns")
public class QstnsDTO {
    @Id
    private String qstn_title;      // 제목
    private String qstn_content;    // 내용
    private Date questioned_at;     // 생성일자
    private String qstn_open;       // 문의 답변여부
    private String mb_email;        // 문의 작성자
    private String qstn_file;       // 문의 첨부파일
}
