package com.example.community.scrap.entity;

import com.example.community.post.entity.Post;
import com.example.common.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access=PROTECTED)
@Builder
@AllArgsConstructor
public class Scrap {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="scrap_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id ")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;


}
