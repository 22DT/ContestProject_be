package com.example.common.user.service.data;

import com.example.common.user.Grade;
import com.example.common.user_detail.service.UserDetailInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class OldUserDomain {
    private Long id;
    private String nickname;
    private String profileImage;
    private String email;

    private String school;
    private String department;
    private Grade grade;

    private String userFields;
    private String jobRole;
    private String teamNumberCode;

    private Double rating;
    private String teamMemberCode;

    private UserDetailInfo userDetail;


    public void setUserDetail(UserDetailInfo userDetail) {
        this.userDetail = userDetail;
    }
}
