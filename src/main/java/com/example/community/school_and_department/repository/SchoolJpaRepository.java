package com.example.community.school_and_department.repository;

import com.example.community.school_and_department.entity.School;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolJpaRepository extends JpaRepository<School, Long> {

    @Query("SELECT s FROM School s WHERE s.schoolName LIKE :prefix% ")
    Slice<School> findAllByPrefix(@Param("prefix") String prefix, Pageable pageable);


    Slice<School> findAllBy(Pageable pageable);

}
