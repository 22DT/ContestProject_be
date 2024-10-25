package com.example.community.user_detail.repository.award;

import com.example.community.user_detail.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AwardJpaRepository extends JpaRepository<Award, Integer> {


    List<Award> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from Award s where s.awardName in :awards and s.user.id=:userId")
    void deleteAll(@Param("awards") List<String> awards, @Param("userId") Long userId);


    void deleteAllByUserId(Long userId);

}
