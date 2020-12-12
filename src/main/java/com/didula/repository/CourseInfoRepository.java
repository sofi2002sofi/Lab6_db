package com.didula.repository;

import com.didula.domain.CourseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfoEntity, Integer> {
}
