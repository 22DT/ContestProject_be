package com.example.community.user_detail.repository;

import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import com.example.community.user_detail.UserDetailType;
import com.example.community.user_detail.entity.UserDetail;
import com.example.community.user_detail.service.UserDetailInfo;
import com.example.community.user_detail.service.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDetailRepositoryImpl implements UserDetailRepository {
    private final UserDetailJpaRepository userDetailJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void saveAll(UserDetailType userDetailType, List<String> details, Long userId) {
        User user = userJpaRepository.getReferenceById(userId);


        for (String detail : details) {
            UserDetail userDetail = UserDetail.builder()
                    .user(user)
                    .detailType(userDetailType)
                    .name(detail)
                    .build();

            userDetailJpaRepository.save(userDetail);
        }

    }

    @Override
    public UserDetailInfo findAllByUser(Long userId) {
        List<UserDetail> findUserDetails = userDetailJpaRepository.findAllByUserId(userId);
         List<String> contestExperiences=new ArrayList<>();
         List<String> awardUrls=new ArrayList<>();
         List<String> certificates=new ArrayList<>();
         List<String> stacks=new ArrayList<>();

         for (UserDetail userDetail : findUserDetails) {
             UserDetailType type=userDetail.getDetailType();
             if(type== UserDetailType.AWARD){
                 awardUrls.add(userDetail.getName());
             }
             else if(type==UserDetailType.CERTIFICATION){
                 certificates.add(userDetail.getName());
             }
             else if(type==UserDetailType.STACK){
                 stacks.add(userDetail.getName());
             }
             else if(type==UserDetailType.CONTEST_EXPERIENCE){
                 contestExperiences.add(userDetail.getName());
             }

         }

         return UserDetailInfo.builder()
                 .awardUrls(awardUrls)
                 .certificates(certificates)
                 .stacks(stacks)
                 .contestExperiences(contestExperiences)
                 .build();


    }

    @Override
    public void deleteAll(UserDetailType userDetailType, List<String> details, Long userId) {
        for (String detail : details) {
            userDetailJpaRepository.deleteUserDetailByUserIdAndDetailType(userId, detail, userDetailType);
        }
    }


    @Override
    public void deleteAll(Long userId) {

    }
}
