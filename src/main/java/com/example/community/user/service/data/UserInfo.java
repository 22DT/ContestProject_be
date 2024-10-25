package com.example.community.user.service.data;

import com.example.community.user.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfo {
    private String nickname;
    private String email;
    private String profileImage;

    private String field;
    private String jobRole;

    private String school;
    private String department;
    private Grade grade;
    private boolean isRatingPublic;
}
