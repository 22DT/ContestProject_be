package com.example.community.comment.service.date;

public record CommentAnonymousDomain(
        Long id,
        boolean isAnonymous,
        Long anonymousNumber
) {
}
