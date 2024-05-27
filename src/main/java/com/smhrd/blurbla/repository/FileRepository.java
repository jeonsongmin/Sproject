package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.FileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileDTO, Long> {
    @Query(value = "SELECT *\n" +
                "FROM tb_file\n" +
                "WHERE mb_email = :mb_email\n" +
                "AND file_name LIKE 'final%'", nativeQuery = true)
    List<FileDTO> myPageSelect(String mb_email);
}
