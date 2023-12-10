package com.test.assignment.repository;

import com.test.assignment.entity.Post;
import com.test.assignment.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
    List<PostTag> findByPost(Post post);
}
