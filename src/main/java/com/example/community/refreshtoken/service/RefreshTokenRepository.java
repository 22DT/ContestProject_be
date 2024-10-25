package com.example.community.refreshtoken.service;

public interface RefreshTokenRepository {
    Long save(String refreshToken, Long userId);
}
