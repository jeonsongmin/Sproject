package com.smhrd.blurbla.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
*   해당 DTO는 결제, 비결제  회원 모두 조회하기 위한 DTO
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AllMemberDTO {
    @Id
    private String mb_email;
    private String mb_pw;
    private String mb_role; // role의 디폴트 값은 M
    private Date joinedAt;
    private Date payedAt;

}
