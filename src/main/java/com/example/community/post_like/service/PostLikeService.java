package com.example.community.post_like.service;

import com.example.community.comment_like.CommentLikeStatus;
import com.example.common.user.service.data.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostLikeService {
    private final PostLikeCreator postLikeCreator;
    private final PostLikeReader postLikeReader;
    private final PostLikeDeleter postLikeDeleter;

    public CommentLikeStatus flip(Long commentId, UserDomain loginUSer) {
        if(postLikeReader.isLiked(commentId, loginUSer.getId())){
            postLikeDeleter.remove(commentId, loginUSer.getId());
            return CommentLikeStatus.UNLIKE;
        }

        postLikeCreator.create(commentId, loginUSer.getId());
        return CommentLikeStatus.LIKE;
    }


}
