package com.test.assignment.repository;

import com.test.assignment.entity.BoardDef;
import com.test.assignment.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByBoardDef_BoardCd(BoardDef.BoardCdType boardCd, Pageable pageable);
}
