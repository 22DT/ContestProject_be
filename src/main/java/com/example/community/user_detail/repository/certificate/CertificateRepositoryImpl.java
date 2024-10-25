package com.example.community.user_detail.repository.certificate;

import com.example.community.user.entity.User;
import com.example.community.user.repository.UserJpaRepository;
import com.example.community.user_detail.UserDetailType;
import com.example.community.user_detail.entity.Certificate;
import com.example.community.user_detail.service.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CertificateRepositoryImpl implements UserDetailRepository {
    private final CertificateJpaRepository certificateJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean support(UserDetailType type) {
        return type == UserDetailType.CERTIFICATION;

    }

    @Override
    public void saveAll(List<String> certificates, Long userId) {
        List<Certificate> certificatesList=new ArrayList<>();

        User user = userJpaRepository.getReferenceById(userId);

        for (String certificate : certificates) {
            Certificate newCertificates = Certificate.builder()
                    .user(user)
                    .certificateName(certificate)
                    .build();

            certificatesList.add(newCertificates);
        }

        certificateJpaRepository.saveAll(certificatesList);
    }

    @Override
    public List<String> findAllByUser(Long userId) {
        List<Certificate> findCertificates = certificateJpaRepository.findAllByUserId(userId);
        return findCertificates.stream().map(Certificate::getCertificateName).toList();
    }

    @Override
    public void deleteAll(List<String> certificates, Long userId) {
        certificateJpaRepository.deleteAll(certificates, userId);
    }

    @Override
    public void deleteAll(Long userId) {
        certificateJpaRepository.deleteAllByUserId(userId);
    }
}
