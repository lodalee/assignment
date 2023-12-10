package com.test.assignment.domain.post.repository;

import com.test.assignment.domain.post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByTag(String tagName);
}
