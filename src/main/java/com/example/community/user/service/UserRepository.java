package com.example.community.user.service;

import com.example.community.user.service.data.UserDomain;
import com.example.community.user.service.data.UserInfo;

public interface UserRepository {
    Long create(UserInfo user);

     UserDomain findById(Long id) ;
     UserDomain findByNickname(String nickname);

    void update(UserDomain user);
    void update(UserDomain user, UserInfo userInfo);
}
