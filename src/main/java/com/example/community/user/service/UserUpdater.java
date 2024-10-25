package com.example.community.user.service;

import com.example.community.user.service.data.UserDomain;
import com.example.community.user.service.data.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdater {
    private final UserRepository userRepository;

    public void update(UserDomain user, UserInfo userInfo) {
        userRepository.update(user, userInfo);
    }

    void withdraw(UserDomain user){
        userRepository.update(user);
    }
}
