package com.smhrd.blurbla.model;

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
@Table(name = "tb_member")
public class Member {
    @Id
    private String mb_email;
    private String mb_pw;
    private String mb_role; // role의 디폴트 값은 M
    private Date joinedAt;
}
