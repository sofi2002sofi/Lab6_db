package com.didula.service.implementation;

import com.didula.domain.GenderEntity;
import com.didula.domain.UserEntity;
import com.didula.exception.NoSuchGenderException;
import com.didula.exception.NoSuchUserException;
import com.didula.repository.GenderRepository;
import com.didula.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService extends GeneralServiceImpl<UserEntity, Integer> {
  private final JpaRepository<UserEntity, Integer> userRepository;
  private final JpaRepository<GenderEntity, Integer> genderRepository;

  public UserService(UserRepository userRepository, GenderRepository genderRepository){
    this.userRepository = userRepository;
    this.genderRepository = genderRepository;
  }

  @Override
  protected JpaRepository<UserEntity, Integer> getRepository() {
    return userRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchUserException();
  }

  @Override
  protected UserEntity mergeEntities(UserEntity newEntity, UserEntity entity) {
    newEntity.setFullName(entity.getFullName() != null ? entity.getFullName() : newEntity.getFullName());
    newEntity.setForumsName(entity.getForumsName() != null ? entity.getForumsName() : newEntity.getForumsName());
    newEntity.setEmail(entity.getEmail() != null ? entity.getEmail() : newEntity.getEmail());
    newEntity.setBirthYear(entity.getBirthYear() != null ? entity.getBirthYear() : newEntity.getBirthYear());
    newEntity.setGenderByGenderId(entity.getGenderByGenderId() != null ? entity.getGenderByGenderId() : newEntity.getGenderByGenderId());

    return newEntity;
  }

  public Set<UserEntity> getUserByGenderId(
          Integer genderId) {
    if (genderRepository.existsById(genderId)) {
      GenderEntity genderEntity =
              genderRepository.findById(genderId).get();
      return genderEntity.getUsers();
    }
    throw new NoSuchGenderException();
  }

}
