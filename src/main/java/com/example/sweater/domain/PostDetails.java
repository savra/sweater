package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Date;


/*дил*/
@Entity(name = "post_details")
public class PostDetails {
    private Long id;
    private Date createdOn;
    private String createdBy;
    private Post post;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_details_seq")
    @SequenceGenerator(name = "post_details_seq", sequenceName = "post_details_seq", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "created_on")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

   /* @JoinColumn(name = "post_id")*/
 /*   @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "post_id")
    public Post getPost() {
        return post;
    }*/

   /* public void setPost(Post post) {
        this.post = post;
    }*/
}
