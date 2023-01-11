package com.example.sweater.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Post")
public class Post {
    private Long id;
    private Set<ImgFormat> title;
    private PostDetails postDetails;

    public enum ImgFormat {
        PNG,
        JPEG,
        SVG;
    }
//в дил ссылка на файл
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ColumnDefault("PNG, JPEG, SVG")
    public Set<ImgFormat> getTitle() {
        return title;
    }

    public void setTitle(Set<ImgFormat> title) {
        this.title = title;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "post")
    public PostDetails getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }
}

