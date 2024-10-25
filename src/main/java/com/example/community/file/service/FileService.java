package com.example.community.file.service;

import com.example.community.file.FileType;
import com.example.community.file.service.data.FileDomain;
import com.example.community.file.service.data.FileInfo;
import com.example.community.file.service.db.FileCreator;
import com.example.community.file.service.db.FileDeleter;
import com.example.community.file.service.db.FileReader;
import com.example.community.file.service.db.FileUpdater;
import com.example.community.file.service.storage.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileCreator fileCreator;
    private final FileReader fileReader;
    private final FileUpdater fileUpdater;
    private final FileDeleter fileDeleter;
    private final FileManager fileManager;

    public String upload(MultipartFile file, FileType type) {
        // 저장소에 파일 저장.
        FileInfo fileInfo = fileManager.upload(file);
        fileInfo.setFileType(type);

        // DB에 파일 저장.
        fileCreator.save(fileInfo);

        return fileManager.getUrl(fileInfo.getStoreFileName());
    }

    public String uploadOnlyStorage(MultipartFile file) {
        FileInfo fileInfo = fileManager.upload(file);
        return fileManager.getUrl(fileInfo.getStoreFileName());
    }

    public List<FileDomain> getFiles(Long PostId){
        return fileReader.getFiles(PostId);
    }

    public FileDomain getThumbnail(Long PostId){
        return fileReader.getThumbnail(PostId);
    }

    public void update(Long postId, List<FileDomain> requestFiles, List<FileDomain> oldFiles) {
        setUrls(oldFiles);
        List<String> oldUrls = oldFiles.stream().map(FileDomain::getUrl).toList();
        List<String> requestUrls = requestFiles.stream().map(FileDomain::getUrl).toList();

        List<String> toDelete = new ArrayList<>(oldUrls);
        toDelete.removeAll(requestUrls);

        if (!toDelete.isEmpty()) {
            fileManager.delete(toDelete);
            fileDeleter.delete(postId, toDelete);
        }

        fileUpdater.associateFilesWithPost(postId, requestFiles);
    }

    private void setUrls(List<FileDomain> files){
        for (FileDomain file : files) {
            String url = fileManager.getUrl(file.getStoreFileName());
            file.setUrl(url);
        }
    }
}
