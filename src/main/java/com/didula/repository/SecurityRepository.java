package com.didula.repository;

import com.didula.domain.SecurityEntity;
import com.didula.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Integer> {
}
