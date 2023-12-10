package com.test.assignment.repository;

import com.test.assignment.entity.BoardDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardDefRepository extends JpaRepository<BoardDef, String> {
    Optional<BoardDef> findByBoardCd(BoardDef.BoardCdType boardCd);
}
