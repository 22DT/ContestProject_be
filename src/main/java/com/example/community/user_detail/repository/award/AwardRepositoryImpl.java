package com.example.community.user_detail.repository.award;

import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import com.example.community.user_detail.UserDetailType;
import com.example.community.user_detail.entity.Award;
import com.example.community.user_detail.service.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AwardRepositoryImpl implements UserDetailRepository {
    private final AwardJpaRepository awardJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean support(UserDetailType type) {
        return type == UserDetailType.AWARD;
    }

    @Override
    public void saveAll(List<String> awards, Long userId) {
        List<Award> awardsList=new ArrayList<>();

        User user = userJpaRepository.getReferenceById(userId);

        for (String award : awards) {
            Award newAward = Award.builder()
                    .user(user)
                    .awardName(award)
                    .build();

            awardsList.add(newAward);
        }

        awardJpaRepository.saveAll(awardsList);
    }

    @Override
    public List<String> findAllByUser(Long userId) {
        List<Award> findAward = awardJpaRepository.findAllByUserId(userId);
        return findAward.stream().map(Award::getAwardName).toList();
    }

    @Override
    public void deleteAll(List<String> awards, Long userId) {
        awardJpaRepository.deleteAll(awards, userId);
    }

    @Override
    public void deleteAll(Long userId) {
        awardJpaRepository.deleteAllByUserId(userId);
    }
}
