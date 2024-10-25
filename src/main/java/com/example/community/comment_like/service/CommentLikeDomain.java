package com.example.community.comment_like.service;


public record CommentLikeDomain(
        Long userId,
        String userNickname,
        Long commentId
) {
}
