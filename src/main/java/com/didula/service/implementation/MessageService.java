package com.didula.service.implementation;

import com.didula.domain.CourseInfoEntity;
import com.didula.domain.MessageEntity;
import com.didula.exception.NoSuchCourseInfoException;
import com.didula.exception.NoSuchMessageException;
import com.didula.repository.CourseInfoRepository;
import com.didula.repository.MessageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageService extends GeneralServiceImpl<MessageEntity, Integer> {
  private final JpaRepository<MessageEntity, Integer> messageRepository;
  private final JpaRepository<CourseInfoEntity, Integer> courseInfoRepository;

  public MessageService(MessageRepository messageRepository, CourseInfoRepository courseInfoRepository){
    this.messageRepository = messageRepository;
    this.courseInfoRepository = courseInfoRepository;
  }

  @Override
  protected JpaRepository<MessageEntity, Integer> getRepository() {
    return messageRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchMessageException();
  }

  @Override
  protected MessageEntity mergeEntities(MessageEntity newEntity, MessageEntity entity) {
    newEntity.setMessageContent(entity.getMessageContent() != null ? entity.getMessageContent() : newEntity.getMessageContent());
    newEntity.setCountOfOverduedTests(entity.getCountOfOverduedTests() != null ? entity.getCountOfOverduedTests() : newEntity.getCountOfOverduedTests());
    newEntity.setCountOfOverduedModules(entity.getCountOfOverduedModules() != null ? entity.getCountOfOverduedModules() : newEntity.getCountOfOverduedModules());
    newEntity.setCourseInfoByCourseInfoId(entity.getCourseInfoByCourseInfoId() != null ? entity.getCourseInfoByCourseInfoId() : newEntity.getCourseInfoByCourseInfoId());

    return newEntity;
  }

  public Set<MessageEntity> getMessageByCourseInfoId(
          Integer courseInfoId) {
    if (courseInfoRepository.existsById(courseInfoId)) {
      CourseInfoEntity courseInfoEntity =
              courseInfoRepository.findById(courseInfoId).get();
      return courseInfoEntity.getMessagesEntity();
    }
    throw new NoSuchCourseInfoException();
  }
}
