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
@Table(name = "tb_test")
public class X_BoardDTO {
    @Id
    private int test_idx;
    private String test_title;
    private String test_context;
    private Date CreatedAt;
    private char test_answer;

}
