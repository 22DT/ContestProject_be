package com.example.community.user.repository;

import com.example.community.user.Role;
import com.example.community.user.entity.User;
import com.example.community.user.service.data.UserDomain;
import com.example.community.user.service.data.UserInfo;
import com.example.community.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Long create(UserInfo user) {
        User newUser = User.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .userFields(user.getField())
                .jobRole(user.getJobRole())
                .school(user.getSchool())
                .department(user.getDepartment())
                .grade(user.getGrade())
                .createAt(LocalDateTime.now())
                .rating(0.0)
                .isRatingPublic(true)
                .role(Role.USER)
                .teamMemberCode(UUID.randomUUID().toString())
                .popularPostNotification(false)
                .commentOnPostNotification(false)
                .replyOnCommentNotification(false)
                .build();


        return userJpaRepository.save(newUser).getId();
    }

    @Override
    public UserDomain findById(Long id) {

        return userJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found")).toDomain();
    }

    @Override
    public UserDomain findByNickname(String nickname) {
        User user = userJpaRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        return user.toDomain();
    }

    @Override
    public void update(UserDomain user) {
        User findUser = userJpaRepository.findById(user.getId()).get();
        findUser.withdraw();
    }

    @Override
    public void update(UserDomain user, UserInfo userInfo) {
        User findUser = userJpaRepository.findById(user.getId()).get();
        findUser.update(userInfo);
    }
}
