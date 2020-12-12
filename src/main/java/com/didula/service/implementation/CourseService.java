package com.didula.service.implementation;

import com.didula.domain.CourseEntity;
import com.didula.exception.NoSuchCourseException;
import com.didula.repository.CourseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends GeneralServiceImpl<CourseEntity, Integer> {
  private final JpaRepository<CourseEntity, Integer> courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  protected JpaRepository<CourseEntity, Integer> getRepository() {
    return courseRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchCourseException();
  }

  @Override
  protected CourseEntity mergeEntities(CourseEntity newEntity, CourseEntity entity) {
    newEntity.setCourseName(entity.getCourseName() != null ? entity.getCourseName() : newEntity.getCourseName());
    newEntity.setDurationInHours(entity.getDurationInHours() != null ? entity.getDurationInHours() : newEntity.getDurationInHours());

    return newEntity;
  }
}
