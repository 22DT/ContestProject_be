package com.example.community.post_like.api;

import com.example.community.comment_like.CommentLikeStatus;
import com.example.community.post_like.service.PostLikeService;
import com.example.common.user.service.data.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/api/community/posts/{post-id}/likes/{login-id}")
    public ResponseEntity<CommentLikeStatus> flip(@PathVariable("post-id") Long postId,
                                                  @PathVariable("login-id") Long loginUserId) {

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(loginUserId)
                .build();

        CommentLikeStatus status = postLikeService.flip(postId, loginUser);
        return ResponseEntity.ok(status);
    }


}
