package com.example.community.scrap.repository;

import com.example.community.post.entity.Post;
import com.example.community.post.repository.PostJpaRepository;
import com.example.community.scrap.entity.Scrap;
import com.example.community.scrap.service.ScrapDomain;
import com.example.community.scrap.service.ScrapRepository;
import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ScrapRepositoryImpl implements ScrapRepository {
    private final ScrapJpaRepository scrapJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;




    @Override
    public boolean isLiked(Long postId, Long userId) {
        return scrapJpaRepository.existsByPost_IdAndUser_Id(postId, userId);
    }

    @Override
    public Long count(Long postId) {
        return scrapJpaRepository.countByPost_Id(postId);
    }

    @Override
    public void delete(ScrapDomain scrapDomain) {
        scrapJpaRepository.deleteByPost_IdAndUser_Id(scrapDomain.postId(), scrapDomain.userId());
    }

    @Override
    public void delete(Long postId, Long userId) {
        scrapJpaRepository.deleteByPost_IdAndUser_Id(postId, userId);
    }

    @Override
    public void deleteAll(Long postId) {
        scrapJpaRepository.deleteAllByPostId(postId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        scrapJpaRepository.deleteAllByUser_Id(userId);
    }


    @Override
    public void save(Long postId, Long userId) {
        User findUser = userJpaRepository.getReferenceById(userId);
        Post findPost = postJpaRepository.getReferenceById(postId);


        Scrap scrap = Scrap.builder()
                .user(findUser)
                .post(findPost)
                .build();

        scrapJpaRepository.save(scrap);
    }
}
