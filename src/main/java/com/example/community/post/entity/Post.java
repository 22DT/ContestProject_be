package com.example.community.post.entity;

import com.example.community.comment.entity.Comment;
import com.example.common.file.entity.File;
import com.example.common.file.service.data.FileDomain;
import com.example.community.post.service.data.*;
import com.example.common.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access=PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class Post {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="writer_id")
    private User writer;

    @OneToMany(mappedBy = "post", fetch=LAZY)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", fetch=LAZY)
    @JsonIgnore
    private List<File> files;


    private String title;
    private String contestTitle;
    private String content;

    private Long viewCount;
    private Long likeCount;

    private LocalDateTime createAt;

    private Long nextAnonymousSeq;



    public PostPreviewDomain toPostPreviewDomain() {
        Long commentCount= (long) getComments().size();

        FileDomain thumbnail=null;

        if(getFiles()!=null && !getFiles().isEmpty()){
            int minIdx=0;
            Long minOrder=getFiles().get(0).getOrderIndex();
            for(File file : getFiles()) {
                if(minOrder>file.getOrderIndex()) {
                    minOrder=file.getOrderIndex();
                    minIdx=minIdx+1;
                }
            }
            thumbnail= getFiles().get(minIdx).toDomain();
        }




        return PostPreviewDomain.builder()
                .postId(id)
                .info(new PostInfo(title, contestTitle, content))
                .writerNickname(getWriter().getNickname())
                .likeCount(likeCount)
                .viewCount(viewCount)
                .createAt(createAt)
                .commentCount(commentCount)
                .thumbnail(thumbnail)
                .build();
    }

    public PostDomain toPostDomain(){
        return PostDomain.builder()
                .id(id)
                .writer(getWriter().toDomain())
                .info(new PostInfo(title, contestTitle, content))
                .viewCount(viewCount)
                .createAt(createAt)
                .nextAnonymousSeq(nextAnonymousSeq)
                .build();
    }


    public PostUpdateDomain toUpdateDomain(){
        List<FileDomain> fileDomains = getFiles().stream().map(File::toDomain).toList();

        return PostUpdateDomain.builder()
                .id(id)
                .writer(getWriter().toDomain())
                .info(new PostInfo(title, contestTitle, content))
                .files(fileDomains)
                .build();
    }

    public void update(PostInfo info) {
        title=info.title();
        contestTitle=info.contestTitle();
        content=info.content();
    }
}
