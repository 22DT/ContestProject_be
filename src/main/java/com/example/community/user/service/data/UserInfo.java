package com.example.community.user.service.data;

import com.example.community.user.Grade;
import com.example.community.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfo {
    private String email;
    private String nickname;
    private String snsProfileImageUrl;

    private Grade grade;
    private String school;
    private String major;

    private Role role;
    private String userField;
    private String duty;

    private boolean isRatingPublic;
}
