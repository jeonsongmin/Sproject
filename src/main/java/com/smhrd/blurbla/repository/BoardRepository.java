package com.smhrd.blurbla.repository;

import com.smhrd.blurbla.model.X_BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<X_BoardDTO, Long> { }

// 해당 레포지토리는 추후 삭제할 예정....