package com.example.community.post.service;

import com.example.common.file.service.FileService;
import com.example.common.file.service.data.FileDomain;
import com.example.common.file.service.db.FileUpdater;
import com.example.community.post.service.data.*;
import com.example.common.user.service.data.UserDomain;
import com.example.common.user.service.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostCreator postCreator;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final PostRepository postRepository;
    private final UserValidator userValidator;
    private final FileService fileService;
    private final FileUpdater fileUpdater;


    public Long write(PostInfo post, List<FileDomain> requestFiles, UserDomain writer){
        // 게시글
        Long postId = postCreator.create(post, writer);

        // 파일
        fileUpdater.associateFilesWithPost(postId, requestFiles);

        return postId;
    }


    public PostDomain getPost(Long postId, UserDomain loginUser) {
        return postReader.getPost(postId, loginUser);
    }

    public Slice<PostPreviewDomain> getPopularPosts(Integer page, Integer size){
        return postReader.getPopularPosts(page, size);
    }

    public Page<PostPreviewDomain> getPosts(Integer page, PostSortType sort) {
        return postReader.getPosts(page, sort);
    }

    public Slice<PostActivityDomain> getPostsByTeamMemberCode(String teamMemberCode, Integer page) {
        return postReader.getPostsByTeamMemberCode(teamMemberCode, page);
    }

    public void update(Long postId, PostInfo postInfo, UserDomain loginUser, List<FileDomain> requestFiles) {
        PostUpdateDomain post = postRepository.findByPostIdJoinWriterAndFilesForUpdate(postId);
        userValidator.isSame(post.getWriter().getId(), loginUser.getId());

        // 파일 수정
        fileService.update(postId, requestFiles, post.getFiles());

        // 게시글 수정
        postUpdater.update(postId, postInfo);
    }

    public void delete(Long postId, UserDomain loginUser) {
        PostDomain postDomain = postRepository.findByPostIdJoinWriter(postId);
        userValidator.isSame(postDomain.getWriter().getId(), loginUser.getId());
        postDeleter.delete(postDomain);
    }

}
