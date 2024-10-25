package com.example.community.file.entity;

import com.example.community.file.FileType;
import com.example.community.file.service.data.FileDomain;
import com.example.community.file.service.data.FileInfo;
import com.example.community.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;


@Entity
@NoArgsConstructor(access=PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class File {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    private Long orderIndex;
    private String storeName;
    private String uploadName;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    private LocalDateTime createAt;


    public FileDomain toDomain(){
        return FileDomain.builder()
                .id(id)
                .info(FileInfo.builder()
                        .storeFileName(storeName)
                        .uploadFileName(uploadName)
                        .fileType(fileType)
                        .build())
                .order(orderIndex)
                .build();
    }

}
