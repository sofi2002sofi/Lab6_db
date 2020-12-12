package com.didula.service.implementation;

import com.didula.domain.CourseInfoEntity;
import com.didula.domain.TestEntity;
import com.didula.exception.NoSuchCourseInfoException;
import com.didula.exception.NoSuchTestException;
import com.didula.repository.CourseInfoRepository;
import com.didula.repository.TestRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TestService extends GeneralServiceImpl<TestEntity, Integer> {
  private final JpaRepository<TestEntity, Integer> testRepository;
  private final JpaRepository<CourseInfoEntity, Integer> courseInfoRepository;

  public TestService(TestRepository testRepository, CourseInfoRepository courseInfoRepository){
    this.testRepository = testRepository;
    this.courseInfoRepository = courseInfoRepository;
  }

  @Override
  protected JpaRepository<TestEntity, Integer> getRepository() {
    return testRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchTestException();
  }

  @Override
  protected TestEntity mergeEntities(TestEntity newEntity, TestEntity entity) {
    newEntity.setTestName(entity.getTestName() != null ? entity.getTestName() : newEntity.getTestName());
    newEntity.setDeadlineDatetime(entity.getDeadlineDatetime() != null ? entity.getDeadlineDatetime() : newEntity.getDeadlineDatetime());
    newEntity.setCompleted(entity.getCompleted() != null ? entity.getCompleted() : newEntity.getCompleted());
    newEntity.setOverdue(entity.getOverdue() != null ? entity.getOverdue() : newEntity.getOverdue());
    newEntity.setCourseInfoByCourseInfoId(entity.getCourseInfoByCourseInfoId() != null ? entity.getCourseInfoByCourseInfoId() : newEntity.getCourseInfoByCourseInfoId());

    return newEntity;
  }

  public Set<TestEntity> getTestByCourseInfoId(
          Integer courseInfoId) {
    if (courseInfoRepository.existsById(courseInfoId)) {
      CourseInfoEntity courseInfoEntity =
              courseInfoRepository.findById(courseInfoId).get();
      return courseInfoEntity.getTestsEntity();
    }
    throw new NoSuchCourseInfoException();
  }
}
