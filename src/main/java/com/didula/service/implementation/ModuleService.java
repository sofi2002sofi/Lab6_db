package com.didula.service.implementation;

import com.didula.domain.CourseInfoEntity;
import com.didula.domain.ModuleEntity;
import com.didula.exception.NoSuchCourseInfoException;
import com.didula.exception.NoSuchModuleException;
import com.didula.repository.CourseInfoRepository;
import com.didula.repository.ModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ModuleService extends GeneralServiceImpl<ModuleEntity, Integer> {
  private final JpaRepository<ModuleEntity, Integer> moduleRepository;
  private final JpaRepository<CourseInfoEntity, Integer> courseInfoRepository;

  public ModuleService(ModuleRepository moduleRepository, CourseInfoRepository courseInfoRepository){
    this.moduleRepository = moduleRepository;
    this.courseInfoRepository = courseInfoRepository;
  }

  @Override
  protected JpaRepository<ModuleEntity, Integer> getRepository() {
    return moduleRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchModuleException();
  }

  @Override
  protected ModuleEntity mergeEntities(ModuleEntity newEntity, ModuleEntity entity) {
    newEntity.setModuleName(entity.getModuleName() != null ? entity.getModuleName() : newEntity.getModuleName());
    newEntity.setDeadlineDatetime(entity.getDeadlineDatetime() != null ? entity.getDeadlineDatetime() : newEntity.getDeadlineDatetime());
    newEntity.setCompleted(entity.getCompleted() != null ? entity.getCompleted() : newEntity.getCompleted());
    newEntity.setOverdue(entity.getOverdue() != null ? entity.getOverdue() : newEntity.getOverdue());
    newEntity.setCourseInfoByCourseInfoId(entity.getCourseInfoByCourseInfoId() != null ? entity.getCourseInfoByCourseInfoId() : newEntity.getCourseInfoByCourseInfoId());

    return newEntity;
  }

  public Set<ModuleEntity> getModuleByCourseInfoId(
          Integer courseInfoId) {
    if (courseInfoRepository.existsById(courseInfoId)) {
      CourseInfoEntity courseInfoEntity =
              courseInfoRepository.findById(courseInfoId).get();
      return courseInfoEntity.getModulesEntity();
    }
    throw new NoSuchCourseInfoException();
  }
}
