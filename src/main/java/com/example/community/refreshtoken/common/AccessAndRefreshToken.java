package com.example.community.refreshtoken.common;

public record AccessAndRefreshToken(
        String accessToken,
        String refreshToken
) {
}
