package com.example.community.comment.service.date;

import com.example.community.user.service.data.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentInfo {
    private UserDomain writerDomain;

    private String content;

    private boolean isReply;
    private Long parentId;

    private boolean isAnonymous;
    private Long anonymousNumber;

    public void anonymize(){
        writerDomain=UserDomain.builder()
                                .id(writerDomain.getId()) // 흠..
                                .nickname("익명"+anonymousNumber)
                                .profileImage(null)
                                .build();

    }


}
