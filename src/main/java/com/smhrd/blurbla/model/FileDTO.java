package com.smhrd.blurbla.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 *   해당 DTO는 업로드 파일에 대한 테이블
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_file")
@Getter
public class FileDTO {
    @Id
    private String file_name;
    private String file_rename;
    private String file_type;
    private Integer file_size;
    private Date CreatedAt;
}

