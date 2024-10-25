package com.example.community.user_detail.repository.certificate;

import com.example.community.user_detail.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateJpaRepository extends JpaRepository<Certificate, Integer> {

    List<Certificate> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from Certificate s where s.certificateName in :certificates and s.user.id=:userId")
    void deleteAll(@Param("certificates")List<String> certificates, @Param("userId") Long userId);

    void deleteAllByUserId(Long userId);


}
