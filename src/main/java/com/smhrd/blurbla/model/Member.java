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
    private String email;
    private String pw;
    // role의 디폴트 값은 M
    private String role;
    private Date joinedAt;
}
