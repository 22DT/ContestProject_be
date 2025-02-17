package com.example.common.user_detail.service;

import com.example.common.user_detail.UserDetailType;

import java.util.List;

public interface UserDetailRepository {

    void saveAll(UserDetailType userDetailType, List<String> details, Long userId);
    UserDetailInfo findAllByUser(Long userId);
    void deleteAll(UserDetailType userDetailType, List<String> details, Long userId);

    void deleteAll(Long userId);
}
