package com.example.common.user.api;

import com.example.community.post.dto.response.PostPreviewResponse;
import com.example.community.refreshtoken.common.AccessAndRefreshToken;
import com.example.common.user.dto.request.SignUpRequest;
import com.example.common.user.dto.request.UserDetailRequest;
import com.example.common.user.dto.request.UserUpdateRequest;
import com.example.common.user.dto.response.MyCommentResponse;
import com.example.common.user.service.UserService;
import com.example.common.user.service.data.UserDomain;
import com.example.common.user.service.data.UserInfo;
import com.example.common.user_detail.service.UserDetailInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users/signup")
    public ResponseEntity<Map> signup(@RequestBody SignUpRequest request) {
        UserInfo user = request.toUserInfo();
        AccessAndRefreshToken tokens = userService.signup(user);

        Map<String, String> apiResponse = new ConcurrentHashMap<>();

        apiResponse.put("accessToken", tokens.accessToken());
        apiResponse.put("refreshToken", tokens.refreshToken());

        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/api/users/details/{user-id}")
    public ResponseEntity<Void> registerUserDetails(@RequestBody UserDetailRequest request,
                                                    @PathVariable("user-id") Long userId) {
        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();

        UserDetailInfo userDetailInfo = request.toUserDetailInfo();
        userService.registerUserDetails(loginUser, userDetailInfo);


        return ResponseEntity.ok().build();
    }



    @PostMapping("/api/my/withdraw/{user-id}")
    public ResponseEntity<Void> withdraw(@PathVariable("user-id") Long userId){
        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();

        userService.withdraw(loginUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/my/profile/{user-id}")
    public ResponseEntity<UserDomain> getProfile(@PathVariable("user-id")Long userId) {

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();
        UserDomain user = userService.getUser(loginUser);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/my/activity/scrap/{user-id}")
    public ResponseEntity<Slice<PostPreviewResponse>> getScrapedPosts(@PathVariable("user-id") Long userId,
                                                                      @RequestParam("page") Integer page,
                                                                      @RequestParam(value = "sort", required = false) String sort){
        if(sort==null){sort="createdAt";}
        if(page==null){page=0;}

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();

        Slice<PostPreviewResponse> posts = userService.getScrapedPosts(page, sort, loginUser).map(PostPreviewResponse::from);
        return ResponseEntity.ok(posts);

    }

    @GetMapping("/api/my/activity/posts/{user-id}")
    public ResponseEntity<Slice<PostPreviewResponse>> getMyPosts(@PathVariable("user-id") Long userId,
                                                                 @RequestParam("page") Integer page){
        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();

        Slice<PostPreviewResponse> postPreviewResponseSlice = userService.getMyPosts(page, loginUser).map(PostPreviewResponse::from);
        return ResponseEntity.ok(postPreviewResponseSlice);
    }

    @GetMapping("/api/my/activity/comments/{user-id}")
    public ResponseEntity<Slice<MyCommentResponse>> getMyComments(@PathVariable("user-id")Long userId,
                                                                  @RequestParam(value = "page", required = false) Integer page){

        if(page==null){page=0;}

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();
        Slice<MyCommentResponse> map = userService.getMyComments(loginUser, page).map(MyCommentResponse::from);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/api/my/profile/{user-id}")
    public ResponseEntity<Void> updateProfile(@RequestBody UserUpdateRequest request,
                                              @PathVariable("user-id")Long userId){

        // 임시로
        UserDomain loginUser = UserDomain.builder()
                .id(userId)
                .build();

        UserInfo userInfo = request.toUserInfo();
        UserDetailInfo userDetailInfo = request.toUserDetailInfo();
        userService.update(loginUser, userInfo, userDetailInfo);


        return ResponseEntity.ok().build();
    }


}
