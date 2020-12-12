package com.didula.dto;

import com.didula.controller.implementation.CourseInfoController;
import com.didula.controller.implementation.TestController;
import com.didula.domain.TestEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class TestDTO extends ResourceSupport {

  private final TestEntity testEntity;

  public TestDTO(TestEntity testEntity) {
    this.testEntity = testEntity;

    add(linkTo(methodOn(TestController.class).getTest(testEntity.getId())).withSelfRel());
    add(linkTo(methodOn(CourseInfoController.class).getCourseInfo(testEntity.getCourseInfoByCourseInfoId().getId())).withRel("course-info"));
  }

  public Integer getTestId() {
    return testEntity.getId();
  }

  public String getTestName() {
    return testEntity.getTestName();
  }

  public String getDeadlineDatetime() {
    return testEntity.getDeadlineDatetime();
  }

  public Byte getCompleted() {
    return testEntity.getCompleted();
  }

  public Byte getOverdue() {
    return testEntity.getOverdue();
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
    TestDTO testDTO = (TestDTO) o;
    return Objects.equals(testEntity, testDTO.testEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), testEntity);
  }
}