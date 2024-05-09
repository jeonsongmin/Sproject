package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { }
