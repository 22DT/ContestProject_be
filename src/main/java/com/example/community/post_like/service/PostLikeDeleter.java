package com.example.community.post_like.service;

import com.example.community.post.service.PostUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostLikeDeleter {
    private final PostLikeRepository postLikeRepository;
    private final PostUpdater postUpdater;

    public void remove(Long postId, Long userId) {
        postUpdater.decreaseLikeCount(postId);
        postLikeRepository.delete(postId, userId);
    }

    public void removeAll(Long postId){
       postLikeRepository.deleteAll(postId);
    }
}
