package com.example.community.user.service.data;

import com.example.community.user.entity.SuspensionStatus;

import java.time.LocalDateTime;

public class NewUserDomain {
    private Long id;
    private NewUserInfo userInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int warningCount;
    private SuspensionStatus status;
    private LocalDateTime suspensionEndTime;

}
