package com.test.assignment.repository;

import com.test.assignment.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByTag(String tagName);
}
