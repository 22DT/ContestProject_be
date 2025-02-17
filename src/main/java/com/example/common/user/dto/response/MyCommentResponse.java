package com.example.common.user.dto.response;

import com.example.community.comment.service.date.MyCommentDomain;

import java.time.LocalDateTime;

public record MyCommentResponse(
        Long commentId,
        String content,
        Long likeCount,
        Long postId,
        String postTitle,
        String postWriterNickname,
        Long postViewCount,
        LocalDateTime postCreatedAt,
        String thumbnailUrl
) {

    public static MyCommentResponse from(MyCommentDomain comment){
        return new MyCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getLikeCount(),
                comment.getPostId(),
                comment.getPostTitle(),
                comment.getPostWriterNickname(),
                comment.getPostViewCount(),
                comment.getPostCreatedAt(),
                comment.getThumbnailUrl()
        );
    }
}
