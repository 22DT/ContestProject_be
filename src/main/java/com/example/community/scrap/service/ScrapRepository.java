package com.example.community.scrap.service;

public interface ScrapRepository {


    boolean isLiked(Long postId, Long userId);

    Long count(Long postId);

    void delete(ScrapDomain scrapDomain);
    void delete(Long postId, Long userId);
    void deleteAll(Long postId);
    void deleteAllByUserId(Long userId);

    void save(Long postId, Long userId);
}
