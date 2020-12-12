package com.didula.service.implementation;

import com.didula.domain.GenderEntity;
import com.didula.exception.NoSuchGenderException;
import com.didula.repository.GenderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GenderService extends GeneralServiceImpl<GenderEntity, Integer> {
  private final JpaRepository<GenderEntity, Integer> genderRepository;

  public GenderService(GenderRepository genderRepository){
    this.genderRepository = genderRepository;
  }

  @Override
  protected JpaRepository<GenderEntity, Integer> getRepository() {
    return genderRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchGenderException();
  }

  @Override
  protected GenderEntity mergeEntities(GenderEntity newEntity, GenderEntity entity) {
    newEntity.setGenderName(entity.getGenderName() != null ? entity.getGenderName() : newEntity.getGenderName());

    return newEntity;
  }
}
