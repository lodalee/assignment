package com.test.assignment.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "TAG")
@NoArgsConstructor
public class Tag {
    @Id
    @Column(name = "TAG_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagNo;

    @Column(name = "TAG")
    private String tag;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<PostTag> postTags;

    public Tag(String tagName) {
        this.tag = tagName;
    }
}
