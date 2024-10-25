package com.example.community.user_detail.repository.contest_experience;

import com.example.community.user_detail.entity.ContestExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContestExperienceJpaRepository extends JpaRepository<ContestExperience, Integer> {

    List<ContestExperience> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from ContestExperience s where s.contestExperienceName in :contestExperiences and s.user.id=:userId")
    void deleteAll(@Param("contestExperiences")List<String> contestExperiences, @Param("userId") Long userId);

    void deleteAllByUserId(Long userId);


}
