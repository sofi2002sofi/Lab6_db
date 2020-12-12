package com.didula.service.implementation;

import com.didula.domain.GenderEntity;
import com.didula.domain.SecurityEntity;
import com.didula.domain.UserEntity;
import com.didula.exception.NoSuchSecurityException;
import com.didula.exception.NoSuchUserException;
import com.didula.repository.GenderRepository;
import com.didula.repository.SecurityRepository;
import com.didula.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityService extends GeneralServiceImpl<SecurityEntity, Integer> {
  private final JpaRepository<SecurityEntity, Integer> securityRepository;
  private final JpaRepository<UserEntity, Integer> userRepository;

  public SecurityService(SecurityRepository securityRepository, UserRepository userRepository){
    this.securityRepository = securityRepository;
    this.userRepository = userRepository;
  }

  @Override
  protected JpaRepository<SecurityEntity, Integer> getRepository() {
    return securityRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchSecurityException();
  }

  @Override
  protected SecurityEntity mergeEntities(SecurityEntity newEntity, SecurityEntity entity) {
    newEntity.setPassword(entity.getPassword() != null ? entity.getPassword() : newEntity.getPassword());
    newEntity.setUserByUserId(entity.getUserByUserId() != null ? entity.getUserByUserId() : newEntity.getUserByUserId());

    return newEntity;
  }

  public SecurityEntity getSecurityByUserId(
          Integer userId) {
    if (userRepository.existsById(userId)) {
      UserEntity userEntity =
              userRepository.findById(userId).get();
      return userEntity.getSecurityBySecurityId();
    }
    throw new NoSuchUserException();
  }

}
