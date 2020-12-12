package com.didula.dto;

import com.didula.controller.implementation.CourseController;
import com.didula.controller.implementation.CourseInfoController;
import com.didula.controller.implementation.UserController;
import com.didula.domain.CourseInfoEntity;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CourseInfoDTO extends ResourceSupport {

  private final CourseInfoEntity courseInfoEntity;

  public CourseInfoDTO(CourseInfoEntity courseInfoEntity) {
    this.courseInfoEntity = courseInfoEntity;

    add(linkTo(methodOn(CourseInfoController.class).getCourseInfo(courseInfoEntity.getId())).withSelfRel());
    add(linkTo(methodOn(CourseController.class).getCourse(courseInfoEntity.getCourseByCourseId().getId())).withRel("course"));
    add(linkTo(methodOn(UserController.class).getUser(courseInfoEntity.getUserByUserId().getId())).withRel("user"));
  }

  public Integer getCourseInfoId() {
    return courseInfoEntity.getId();
  }

  public String getStartDate() {
    return courseInfoEntity.getStartDate();
  }

  public BigDecimal getCompletionInPercents() {
    return courseInfoEntity.getCompletionInPercents();
  }

  public Byte getAvailableMessage() {
    return courseInfoEntity.getAvailableMessage();
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
    CourseInfoDTO that = (CourseInfoDTO) o;
    return courseInfoEntity.equals(that.courseInfoEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), courseInfoEntity);
  }
}