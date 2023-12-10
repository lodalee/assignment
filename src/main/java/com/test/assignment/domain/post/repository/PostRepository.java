package com.test.assignment.domain.post.repository;

import com.test.assignment.domain.post.entity.BoardDef;
import com.test.assignment.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByBoardDef_BoardCd(BoardDef.BoardCdType boardCd, Pageable pageable);
}
