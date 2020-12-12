package com.didula.service.implementation;

import com.didula.domain.*;
import com.didula.exception.NoSuchCourseException;
import com.didula.exception.NoSuchCourseInfoException;
import com.didula.exception.NoSuchUserException;
import com.didula.repository.CourseInfoRepository;
import com.didula.repository.CourseRepository;
import com.didula.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CourseInfoService extends GeneralServiceImpl<CourseInfoEntity, Integer> {
  private final JpaRepository<CourseInfoEntity, Integer> courseInfoRepository;
  private final JpaRepository<CourseEntity, Integer> courseRepository;
  private final JpaRepository<UserEntity, Integer> userRepository;

  public CourseInfoService(CourseInfoRepository courseInfoRepository, CourseRepository courseRepository, UserRepository userRepository){
    this.courseInfoRepository = courseInfoRepository;
    this.courseRepository = courseRepository;
    this.userRepository = userRepository;
  }

  @Override
  protected JpaRepository<CourseInfoEntity, Integer> getRepository() {
    return courseInfoRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchCourseInfoException();
  }

  @Override
  protected CourseInfoEntity mergeEntities(CourseInfoEntity newEntity, CourseInfoEntity entity) {
    newEntity.setStartDate(entity.getStartDate() != null ? entity.getStartDate() : newEntity.getStartDate());
    newEntity.setCompletionInPercents(entity.getCompletionInPercents() != null ? entity.getCompletionInPercents() : newEntity.getCompletionInPercents());
    newEntity.setAvailableMessage(entity.getAvailableMessage() != null ? entity.getAvailableMessage() : newEntity.getAvailableMessage());
    newEntity.setCourseByCourseId(entity.getCourseByCourseId() != null ? entity.getCourseByCourseId() : newEntity.getCourseByCourseId());
    newEntity.setUserByUserId(entity.getUserByUserId() != null ? entity.getUserByUserId() : newEntity.getUserByUserId());

    return newEntity;
  }

  public Set<CourseInfoEntity> getCoursesInfoByCourseId(
          Integer courseId) {
    if (courseRepository.existsById(courseId)) {
      CourseEntity courseEntity =
              courseRepository.findById(courseId).get();
      return courseEntity.getCoursesInfo();
    }
    throw new NoSuchCourseException();
  }

  public Set<CourseInfoEntity> getCoursesInfoByUserId(
          Integer userId) {
    if (userRepository.existsById(userId)) {
      UserEntity userEntity =
              userRepository.findById(userId).get();
      return userEntity.getCoursesInfo();
    }
    throw new NoSuchUserException();
  }

}
