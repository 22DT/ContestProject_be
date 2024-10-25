package com.example.community.scrap.api;

import com.example.community.scrap.service.ScrapRepository;
import com.example.community.scrap.service.ScrapService;
import com.example.community.scrap.ScrapStatus;
import com.example.community.user.service.data.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;
    private final ScrapRepository scrapRepository;

    @PostMapping("/api/community/posts/{post-id}/scraps/{login-id}")
    public ResponseEntity<ScrapStatus> flip(@PathVariable("post-id") Long postId,
                                            @PathVariable("login-id") Long loginUserId) {

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(loginUserId)
                .build();

        ScrapStatus status = scrapService.flip(postId, loginUser);
        return ResponseEntity.ok(status);
    }


}
