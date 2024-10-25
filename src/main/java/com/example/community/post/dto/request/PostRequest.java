package com.example.community.post.dto.request;

import com.example.community.post.service.data.PostInfo;

import java.util.List;

public record PostRequest(
        String title,
        String contestTitle,
        String content,
        List<FileRequest> files
) {

    public PostInfo toPostInfo(){
        return new PostInfo(title, contestTitle, content);
    }

}
