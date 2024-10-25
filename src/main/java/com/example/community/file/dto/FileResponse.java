package com.example.community.file.dto;

import com.example.community.file.FileType;
import com.example.community.file.service.data.FileDomain;

import java.util.ArrayList;
import java.util.List;

public record FileResponse(
        List<String> imageUrls,
        List<AttachmentResponse> attachments
) {

    public static FileResponse fromDomain(List<FileDomain> files) {
        List<String> images = new ArrayList<>();
        List<AttachmentResponse> attachments=new ArrayList<>();

        for (FileDomain file : files) {
            if(file.getInfo().getFileType()== FileType.IMAGE){
                images.add(file.getUrl());
            }
            else if(file.getInfo().getFileType()==FileType.ATTACHMENT){
                attachments.add(new AttachmentResponse(file.getInfo().getUploadFileName(), file.getUrl()));
            }
        }

        return new FileResponse(images, attachments);
    }
}
