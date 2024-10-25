package com.example.community.post_like.entity;

import com.example.community.post.entity.Post;
import com.example.community.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Builder
@AllArgsConstructor
public class PostLike {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="post_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
