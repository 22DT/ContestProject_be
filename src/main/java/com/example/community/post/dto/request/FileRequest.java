package com.example.community.post.dto.request;

import com.example.common.file.service.data.FileDomain;

public record FileRequest(
        Long order,
        String url
) {

    public FileDomain toFileDomain(){
        return FileDomain.builder()
                .order(order)
                .url(url).build();
    }
}
