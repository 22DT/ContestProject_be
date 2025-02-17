package com.example.common.file.service.db;

import com.example.common.file.service.data.FileDomain;
import com.example.common.file.service.storage.FileManager;
import com.example.common.file.service.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileUpdater {
    private final FileRepository fileRepository;
    private final FileManager fileManager;

    public void associateFilesWithPost(Long postId, Map<Long, String> orderAndUrl) {
        if (orderAndUrl == null || orderAndUrl.isEmpty()) {return;}
        Map<Long, String> orderAndStoreFileName = new ConcurrentHashMap<>();

        for (Long order : orderAndUrl.keySet()) {
            String url = orderAndUrl.get(order);
            String storeFilName = fileManager.getStoreFileName(url);
            orderAndStoreFileName.put(order, storeFilName);
        }

        fileRepository.updateAllByPost(postId, orderAndStoreFileName);
    }

    public void associateFilesWithPost(Long postId, List<FileDomain> requestFiles) {

        if(requestFiles == null || requestFiles.isEmpty()) {return;}

        requestFiles.sort(Comparator.comparing(FileDomain::getOrder));

        long seq=0L;
        for (FileDomain requestFile : requestFiles) {
            String url = requestFile.getUrl();
            String storeFilName = fileManager.getStoreFileName(url);
            requestFile.setStoreFileName(storeFilName);
            requestFile.setOrder(seq++);
        }

        fileRepository.updateAllByPost(postId, requestFiles);
    }


}
