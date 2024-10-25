package com.example.community.file.service.db;

import com.example.community.file.service.FileRepository;
import com.example.community.file.service.data.FileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FileCreator {
    private final FileRepository fileRepository;


    public Long save(FileInfo fileInfo) {
        return fileRepository.save(fileInfo);
    }

}
