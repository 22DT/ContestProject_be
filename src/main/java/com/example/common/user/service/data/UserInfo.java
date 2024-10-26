package com.example.common.user.service.data;

import com.example.common.user.Grade;
import com.example.common.user.Role;
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
