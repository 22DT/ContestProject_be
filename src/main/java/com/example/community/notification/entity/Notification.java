package com.example.community.notification.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Notification {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="notification_id")
    public Long id;

    
}
