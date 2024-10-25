package com.example.community.user.entity;

import com.example.community.user.Grade;
import com.example.community.user.Role;
import com.example.community.user.service.data.UserDomain;
import com.example.community.user.service.data.UserInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access=PROTECTED)
@AllArgsConstructor(access=PROTECTED)
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String nickname;
    private String profileImage;
    private String email;
    private String password;

    private String school; // (수정)타입
    private String department; // (수정)타입
    @Enumerated(STRING)
    private Grade grade;

    private String userFields;  // 관심 분야?
    private String jobRole;  // (추가)역할

    private Double rating;
    private boolean isRatingPublic;  // (추가)

    @Enumerated(STRING)
    private Role role;

    private String teamMemberCode;  // (추가) 팀원 코드

    private LocalDateTime createAt;
    private boolean isDeleted; // (추가) 탈퇴 유무

    private boolean popularPostNotification;  // (추가) 인기글
    private boolean commentOnPostNotification;  // (추가) 댓글
    private boolean replyOnCommentNotification;   // (추가) 대댓글


    public UserDomain toDomain(){
        return UserDomain.builder()
                .id(id)
                .nickname(nickname)
                .profileImage(profileImage)
                .email(email)
                .school(school)
                .department(department)
                .grade(grade)
                .userFields(userFields)
                .jobRole(jobRole)
                .rating(rating)
                .teamMemberCode(teamMemberCode)
                .build();
    }

    public static User from(UserDomain domain){
        return User.builder()
                .id(domain.getId())
                .nickname(domain.getNickname())
                .profileImage(domain.getProfileImage())
                .build();
    }


    public void withdraw(){
        nickname="(알수없음)";
        email=null;
        profileImage=null;
        /*
        * 다른 것도?
        * */
    }

    public void update(UserInfo userInfo){
        nickname=userInfo.getNickname();
        profileImage=userInfo.getProfileImage();
        department=userInfo.getDepartment();
        grade=userInfo.getGrade();
        userFields=userInfo.getField();
        jobRole=userInfo.getJobRole();
    }

}
