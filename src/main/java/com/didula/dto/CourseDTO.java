package com.didula.dto;

import com.didula.controller.implementation.CourseController;
import com.didula.domain.CourseEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CourseDTO extends ResourceSupport {

  private final CourseEntity courseEntity;

  public CourseDTO(CourseEntity courseEntity) {
    this.courseEntity = courseEntity;

    add(linkTo(methodOn(CourseController.class).getCourse(courseEntity.getId())).withSelfRel());
  }

  public Integer getCourseId() {
    return courseEntity.getId();
  }

  public String getCourseName() {
    return courseEntity.getCourseName();
  }

  public Double getDurationInHours() {
    return courseEntity.getDurationInHours();
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
    CourseDTO courseDTO = (CourseDTO) o;
    return courseEntity.equals(courseDTO.courseEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), courseEntity);
  }
}
