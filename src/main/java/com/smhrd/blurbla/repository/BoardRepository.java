package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Long> { }
