package com.example.community.user_detail.repository.stack;

import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import com.example.community.user_detail.UserDetailType;
import com.example.community.user_detail.entity.Stack;
import com.example.community.user_detail.service.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StackRepositoryImpl implements UserDetailRepository {
    private final StackJpaRepository stackJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean support(UserDetailType type) {
        return type == UserDetailType.STACK;
    }

    @Override
    public void saveAll(List<String> stacks, Long userId) {
        List<Stack> stacksList=new ArrayList<>();

        User user = userJpaRepository.getReferenceById(userId);

        for (String stack : stacks) {
            Stack newStacks = Stack.builder()
                    .user(user)
                    .stackName(stack)
                    .build();

            stacksList.add(newStacks);
        }

        stackJpaRepository.saveAll(stacksList);
    }

    @Override
    public List<String> findAllByUser(Long userId) {
        List<Stack> findStacks = stackJpaRepository.findAllByUserId(userId);
        return findStacks.stream().map(Stack::getStackName).toList();
    }

    @Override
    public void deleteAll(List<String> stacks, Long userId) {
        stackJpaRepository.deleteAll(stacks, userId);
    }

    @Override
    public void deleteAll(Long userId) {
        stackJpaRepository.deleteAllByUserId(userId);
    }
}
