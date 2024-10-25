package com.example.community.user_detail.repository.contest_experience;

import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import com.example.community.user_detail.UserDetailType;
import com.example.community.user_detail.entity.ContestExperience;
import com.example.community.user_detail.service.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContestExperienceRepositoryImpl implements UserDetailRepository {
    private final ContestExperienceJpaRepository contestExperienceJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean support(UserDetailType type) {
        return type == UserDetailType.CONTEST_EXPERIENCE;

    }

    @Override
    public void saveAll(List<String> contestExperiences, Long userId) {
        List<ContestExperience> contestExperiencesList=new ArrayList<>();

        User user = userJpaRepository.getReferenceById(userId);

        for (String contestExperience : contestExperiences) {
            ContestExperience newContestExperiences = ContestExperience.builder()
                    .user(user)
                    .contestExperienceName(contestExperience)
                    .build();

            contestExperiencesList.add(newContestExperiences);
        }

        contestExperienceJpaRepository.saveAll(contestExperiencesList);
    }

    @Override
    public List<String> findAllByUser(Long userId) {
        List<ContestExperience> findContestExperiences = contestExperienceJpaRepository.findAllByUserId(userId);
        return findContestExperiences.stream().map(ContestExperience::getContestExperienceName).toList();
    }

    @Override
    public void deleteAll(List<String> contestExperiences, Long userId) {
        contestExperienceJpaRepository.deleteAll(contestExperiences, userId );
    }

    @Override
    public void deleteAll(Long userId) {
        contestExperienceJpaRepository.deleteAllByUserId(userId);
    }
}
