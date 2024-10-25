package com.example.community.post.dto.response;

import com.example.community.post.service.data.PostDomain;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,

        Long writerId,
        String writerNickname,
        String writerProfileUrl,
        boolean isWriter,

        String title,
        String contestTitle,
        String content,

        Long viewCount,

        LocalDateTime createdAt,

        Long scrapCount,
        boolean isScraped,

        Long likeCount,
        boolean isLiked
) {

    public static PostResponse from(PostDomain domain){
        return new PostResponse(
                domain.getId(),
                domain.getWriter().getId(),
                domain.getWriter().getNickname(),
                domain.getWriter().getProfileImage(),
                domain.isWriter(),
                domain.getInfo().title(),
                domain.getInfo().contestTitle(),
                domain.getInfo().content(),
                domain.getViewCount(),
                domain.getCreateAt(),
                domain.getScrapCount(),
                domain.isScraped(),
                domain.getLikeCount(),
                domain.isLiked()
        );

    }
}
