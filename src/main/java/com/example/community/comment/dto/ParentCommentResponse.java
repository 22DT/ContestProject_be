package com.example.community.comment.dto;

import com.example.community.comment.service.date.ChildCommentDomain;
import com.example.community.comment.service.date.ParentCommentDomain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ParentCommentResponse(
        Long commentId,

        Long writerId,
        String writerNickname,
        String profileUrl,

        String content,

        LocalDateTime createAt,
        boolean isDeleted,

        boolean isWriter,
        Long likeCount,
        boolean isLiked,
        boolean isNotified,   // 나중에 추가.

        List<ChildCommentResponse> replies


) {

    public static ParentCommentResponse from(ParentCommentDomain domain) {
        List<ChildCommentResponse> child = new ArrayList<>();

        for (ChildCommentDomain childDomain : domain.getReplies()) {
            child.add(ChildCommentResponse.from(childDomain));
        }

        return new ParentCommentResponse(
                domain.getId(),
                domain.getInfo().getWriterDomain().getId(),
                domain.getInfo().getWriterDomain().getNickname(),
                domain.getInfo().getWriterDomain().getProfileImage(),
                domain.getInfo().getContent(),
                domain.getCreateAt(),
                domain.isDeleted(),
                domain.isWriter(),
                domain.getCommentLikeCount(),
                domain.isLiked(),
                false,
                child
        );
    }
}
