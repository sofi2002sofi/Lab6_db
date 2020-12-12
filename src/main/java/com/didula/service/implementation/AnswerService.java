package com.didula.service.implementation;

import com.didula.domain.AnswerEntity;
import com.didula.exception.NoSuchAnswerException;
import com.didula.repository.AnswerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends GeneralServiceImpl<AnswerEntity, Integer> {
  private final JpaRepository<AnswerEntity, Integer> answerRepository;

  public AnswerService(AnswerRepository answerRepository){
    this.answerRepository = answerRepository;
  }

  @Override
  protected JpaRepository<AnswerEntity, Integer> getRepository() {
    return answerRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchAnswerException();
  }

  @Override
  protected AnswerEntity mergeEntities(AnswerEntity newEntity, AnswerEntity entity) {
    newEntity.setAnswerName(entity.getAnswerName() != null ? entity.getAnswerName() : newEntity.getAnswerName());

    return newEntity;
  }
}