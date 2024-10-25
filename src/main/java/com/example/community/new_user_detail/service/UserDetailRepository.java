package com.example.community.new_user_detail.service;

import com.example.community.user_detail.UserDetailType;

import java.util.List;

public interface UserDetailRepository {

    void saveAll(List<String> contestExperiences, Long userId);
    List<String> findAllByUser(Long userId);
    void deleteAll(List<String> contestExperiences,Long userId);

    void deleteAll(Long userId);
}
