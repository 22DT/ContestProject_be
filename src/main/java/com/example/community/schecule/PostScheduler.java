package com.example.community.schecule;

import com.example.community.post.service.PostRepository;
import com.example.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostScheduler {
    private final PostRepository postRepository;


//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    @Scheduled(fixedDelay = 30000) // 30초마다 실행
    public void deleteDrafts() {
        log.info("deleteDrafts= {}", LocalDateTime.now());
//        postRepository.deleteDrafts();
    }
}
