package com.example.community.post.service;


import com.example.community.post.service.data.PostInfo;
import com.example.common.user.service.data.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostCreator {
    private final PostRepository postRepository;

    public Long create(PostInfo post, UserDomain user){
        return postRepository.save(post, user.getId());
    }

}
