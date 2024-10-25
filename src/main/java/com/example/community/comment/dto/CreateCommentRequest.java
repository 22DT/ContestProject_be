package com.example.community.comment.dto;

public record CreateCommentRequest(
        String content,
        boolean isReply,
        Long parentId,
        Boolean isAnonymous
) {
}
