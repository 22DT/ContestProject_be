package com.example.community.comment.dto;

import com.example.community.comment.service.date.ChildCommentDomain;

import java.time.LocalDateTime;

public record ChildCommentResponse(
        Long commentId,

        Long writerId,
        String writerNickname,
        String profileUrl,

        String content,

        LocalDateTime createAt,

        boolean isWriter,
        Long likeCount,
        boolean isLiked,
        boolean isNotified  // 나중에 추가.

) {

    public static ChildCommentResponse from(ChildCommentDomain domain) {
        return new ChildCommentResponse(
                domain.getId(),
                domain.getInfo().getWriterDomain().getId(),
                domain.getInfo().getWriterDomain().getNickname(),
                domain.getInfo().getWriterDomain().getProfileImage(),
                domain.getInfo().getContent(),
                domain.getCreateAt(),
                domain.isWriter(),
                domain.getCommentLikeCount(),
                domain.isLiked(),
                false
        );
    }
}
