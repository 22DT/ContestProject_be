package com.example.community.comment_like.entity;

import com.example.community.comment.entity.Comment;
import com.example.community.comment_like.service.CommentLikeDomain;
import com.example.common.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access=PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="comment_like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;


    public CommentLikeDomain toDomain(){
        return new CommentLikeDomain(getUser().getId(), getUser().getNickname(), getComment().getId());
    }

}
