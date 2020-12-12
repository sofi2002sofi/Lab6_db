package com.didula.dto;

import com.didula.controller.implementation.CourseInfoController;
import com.didula.controller.implementation.MessageController;
import com.didula.domain.MessageEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MessageEntityDTO extends ResourceSupport {

  private final MessageEntity messageEntity;

  public MessageEntityDTO(MessageEntity messageEntity) {
    this.messageEntity = messageEntity;

    add(linkTo(methodOn(MessageController.class).getMessage(messageEntity.getId())).withSelfRel());
    add(linkTo(methodOn(CourseInfoController.class).getCourseInfo(messageEntity.getCourseInfoByCourseInfoId().getId())).withRel("course-info"));
  }

  public Integer getMessageId() {
    return messageEntity.getId();
  }

  public String getMessageContent() {
    return messageEntity.getMessageContent();
  }

  public Integer getCountOfOverduedTests() {
    return messageEntity.getCountOfOverduedTests();
  }

  public Integer getCountOfOverduedModules() {
    return messageEntity.getCountOfOverduedModules();
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
    MessageEntityDTO that = (MessageEntityDTO) o;
    return messageEntity.equals(that.messageEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), messageEntity);
  }
}