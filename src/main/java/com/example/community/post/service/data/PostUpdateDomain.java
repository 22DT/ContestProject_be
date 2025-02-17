package com.example.community.post.service.data;

import com.example.common.file.service.data.FileDomain;
import com.example.common.user.service.data.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostUpdateDomain {
    private Long id;

    private UserDomain writer;

    private PostInfo info;

    private List<FileDomain> files;



}
