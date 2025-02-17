package com.example.common.user.service;

import com.example.common.user.service.data.UserDomain;
import com.example.common.user.service.data.UserInfo;
import com.example.community.comment.service.CommentReader;
import com.example.community.comment.service.date.MyCommentDomain;
import com.example.community.comment_like.service.CommentLikeRepository;
import com.example.community.post.service.PostReader;
import com.example.community.post.service.data.PostPreviewDomain;
import com.example.community.post_like.service.PostLikeRepository;
import com.example.community.refreshtoken.common.AccessAndRefreshToken;
import com.example.community.refreshtoken.common.JwtTokenUtil;
import com.example.community.refreshtoken.service.RefreshTokenRepository;
import com.example.community.scrap.service.ScrapRepository;
import com.example.common.user_detail.service.UserDetailInfo;
import com.example.common.user_detail.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserCreator userCreator;
    private final UserReader userReader;
    private final UserUpdater userUpdater;

    private final PostReader postReader;
    private final CommentReader commentReader;
    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ScrapRepository scrapRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailService userDetailService;

    public UserDomain getUser(UserDomain user){

        UserDomain findUser = userReader.getUser(user.getId());
        UserDetailInfo userDetail = userDetailService.getUserDetail(findUser);

        findUser.setUserDetail(userDetail);
        return findUser;
    }

    /**
     *
     * @param user
     * @return
     *
     * @apiNote
     * 회원 가입 후 로그인시키고 싶은데... 왜 자동 로그인 허허허..
     * 1. 회원 정보 DB에 저장
     * 2. access token, refresh token 발급
     * 3. refresh token DB 저장.
     *
     */
    public AccessAndRefreshToken signup(UserInfo user){
        // 1. 회원 정보 DB에 저장
        Long userId = userCreator.signup(user);

        // 2. 토큰 발급
        String accessToken = JwtTokenUtil.generateAccessToken(userId);
        String refreshToken = JwtTokenUtil.generateRefreshToken(userId);

        // 3. refresh token DB에 저장.
        refreshTokenRepository.save(refreshToken, userId);


        return new AccessAndRefreshToken(accessToken, refreshToken);
    }

    public Slice<PostPreviewDomain> getScrapedPosts(Integer page, String sort, UserDomain loginUser) {

        return postReader.getScrapedPosts(page, sort, loginUser);
    }

    public Slice<PostPreviewDomain> getMyPosts(Integer page, UserDomain loginUser) {
           return postReader.getMyPosts(page, loginUser);
    }

    public Slice<MyCommentDomain> getMyComments(UserDomain loginUser, Integer page) {
        return commentReader.getMyComments(loginUser, page);
    }


    public void registerUserDetails(UserDomain user, UserDetailInfo detailsToUpdate) {
       userDetailService.update(detailsToUpdate, user);
    }

    public void update(UserDomain user, UserInfo userInfo,UserDetailInfo detailsToUpdate) {
        UserDetailInfo userDetail = userDetailService.getUserDetail(user);
        // 기본 정보 업데이트
        userUpdater.update(user, userInfo);

        // 세부 정보 업데이트
        userDetailService.update(detailsToUpdate, user);
    }


    /**
     *
     * @param user
     *
     * @apiNote
     * user: 개인정보 삭제 후 닉네임 (알수없음)
     * 게시글, 댓글: 건들 x
     * 좋아요, 스크랩: 삭제
     * 세부정보: 삭제
     */
    public void withdraw(UserDomain user){
        userUpdater.withdraw(user);
        postLikeRepository.deleteALlByUserId(user.getId());
        commentLikeRepository.deleteAllByUserId(user.getId());
        scrapRepository.deleteAllByUserId(user.getId());
        // 세부 정보 삭제


        userDetailService.update(null, user);

    }
}
