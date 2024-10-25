package com.example.community.user_detail.repository.stack;

import com.example.community.user_detail.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StackJpaRepository extends JpaRepository<Stack, Integer> {

    List<Stack> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from Stack s where s.stackName in :stacks and s.user.id=:userId")
    void deleteAll(@Param("stacks")List<String> stacks, @Param("userId") Long userId);

    void deleteAllByUserId(Long userId);


}
