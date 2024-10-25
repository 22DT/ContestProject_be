package com.example.community.user.dto.request;

import com.example.community.user.Grade;
import com.example.community.user.service.data.UserInfo;

public record SignUpRequest(
        String nickname,
        String email,
        String profileImage,

        String field,
        String jobRole,

        String school,
        String department,
        Grade grade

) {

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .nickname(nickname)
                .email(email)
                .profileImage(profileImage)
                .field(field)
                .jobRole(jobRole)
                .school(school)
                .department(department)
                .grade(grade)
                .build();
    }
}
