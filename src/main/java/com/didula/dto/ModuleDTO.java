package com.didula.dto;

import com.didula.controller.implementation.CourseInfoController;
import com.didula.controller.implementation.ModuleController;
import com.didula.domain.ModuleEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ModuleDTO extends ResourceSupport {

  private final ModuleEntity moduleEntity;

  public ModuleDTO(ModuleEntity moduleEntity) {
    this.moduleEntity = moduleEntity;

    add(linkTo(methodOn(ModuleController.class).getModule(moduleEntity.getId())).withSelfRel());
    add(linkTo(methodOn(CourseInfoController.class).getCourseInfo(moduleEntity.getCourseInfoByCourseInfoId().getId())).withRel("course-info"));
  }

  public Integer getModuleId() {
    return moduleEntity.getId();
  }

  public String getModuleName() {
    return moduleEntity.getModuleName();
  }

  public String getDeadlineDatetime() {
    return moduleEntity.getDeadlineDatetime();
  }

  public Byte getCompleted() {
    return moduleEntity.getCompleted();
  }

  public Byte getOverdue() {
    return moduleEntity.getOverdue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ModuleDTO moduleDTO = (ModuleDTO) o;
    return moduleEntity.equals(moduleDTO.moduleEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), moduleEntity);
  }
}
