package com.example.community.user_detail.service;

import com.example.community.user.entity.User;
import com.example.community.user.service.data.UserDomain;
import com.example.community.user_detail.UserDetailType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService {
    private final List<UserDetailRepository> userDetailRepositoryList;

    public UserDetailInfo getUserDetail(UserDomain user) {
        Map<UserDetailType, List<String>> map = new ConcurrentHashMap<>();
        for (UserDetailType type : UserDetailType.values()) {
            map.put(type, new ArrayList<>());
        }

        for (UserDetailType type : UserDetailType.values()) {
            UserDetailRepository userDetailRepository = getUserDetailRepository(type);

            if (userDetailRepository == null) {
                log.info("userDetailRepository is null");
                return null;
            }

            List<String> currentDetails = userDetailRepository.findAllByUser(user.getId());
            map.get(type).addAll(currentDetails);

        }


        return UserDetailInfo.builder()
                .contestExperiences(map.get(UserDetailType.CONTEST_EXPERIENCE))
                .awardUrls(map.get(UserDetailType.AWARD))
                .certificates(map.get(UserDetailType.CERTIFICATION))
                .stacks(map.get(UserDetailType.STACK))
                .build();
    }


    public void update(UserDetailType detailType, List<String> updateRequest, Long userId){
        UserDetailRepository userDetailRepository = getUserDetailRepository(detailType);

        if(userDetailRepository == null){
            log.info("userDetailRepository is null");
            return;
        }

        List<String> currentDetails = userDetailRepository.findAllByUser(userId);

        if(updateRequest == null){updateRequest = new ArrayList<>();}
        // 지울 거.
        List<String> toDelete=new ArrayList<>(currentDetails);
        toDelete.removeAll(updateRequest);
        if(!toDelete.isEmpty()) {userDetailRepository.deleteAll(toDelete, userId);}

        // 추가할 거.
        List<String> toAdd=new ArrayList<>(updateRequest);
        toAdd.removeAll(currentDetails);
        if(!toAdd.isEmpty()){userDetailRepository.saveAll(toAdd, userId);}
    }


    private UserDetailRepository getUserDetailRepository(UserDetailType detailType){
        for (UserDetailRepository userDetailRepository : userDetailRepositoryList) {
            if(userDetailRepository.support(detailType)){
                return userDetailRepository;
            }
        }
        return null;
    }


}
