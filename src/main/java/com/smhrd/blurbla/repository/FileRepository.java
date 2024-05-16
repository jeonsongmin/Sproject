package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.FileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDTO, Long> { }
