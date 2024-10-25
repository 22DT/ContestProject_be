package com.example.community.file.service;

import com.example.community.file.service.data.FileDomain;
import com.example.community.file.service.data.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileRepository {

    Long save(FileInfo fileInfo);

    List<FileDomain> findAllByPostId(Long postId);
    FileDomain findThumbnailByPostId(Long postId);
    List<FileDomain> findOrphanFiles();

    void updateAllByPost(Long postId, Map<Long, String> orderAndStoreFileName);
    void updateAllByPost(Long postId, List<FileDomain> files);
    void deleteAllByPostId(Long postId, List<String> storeFileNames);
    void deleteAll(List<FileDomain> files);
    void delete(Long postId);


}
