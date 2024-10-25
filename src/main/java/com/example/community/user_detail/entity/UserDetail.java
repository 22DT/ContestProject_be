package com.example.community.user_detail.entity;

import com.example.community.user.entity.User;
import com.example.community.user_detail.UserDetailType;
import jakarta.persistence.*;

@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User user;

    private String what;


    private UserDetailType detailType;
}
