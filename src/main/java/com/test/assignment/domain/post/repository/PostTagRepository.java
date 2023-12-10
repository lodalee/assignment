package com.test.assignment.domain.post.repository;

import com.test.assignment.domain.post.entity.Post;
import com.test.assignment.domain.post.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
    List<PostTag> findByPost(Post post);
}
