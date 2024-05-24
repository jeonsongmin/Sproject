package com.smhrd.blurbla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.Date;

/*
*   해당 DTO는 회원과 관리자 정보가 담긴 테이블
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_member")
public class MemberDTO {
    @Id
    private String mb_email;
    private String mb_pw;
    private String mb_role; // role의 디폴트 값은 M
    private Date joinedAt;

    private Date payedAt;

}
