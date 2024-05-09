package com.smhrd.blurbla.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_file")
public class File {
    @Id
    private String file_name;
    private String file_rename;
    private String file_type;
    private Integer file_size;
    private Date CreatedAt;
}

