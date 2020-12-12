package com.didula.dto;

import com.didula.controller.implementation.AnswerController;
import com.didula.domain.AnswerEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AnswerDTO extends ResourceSupport {

  private final AnswerEntity answerEntity;

  public AnswerDTO(AnswerEntity answerEntity) {
    this.answerEntity = answerEntity;

    add(linkTo(methodOn(AnswerController.class).getAnswer(answerEntity.getId())).withSelfRel());
  }

  public Integer getAnswerId() {
    return answerEntity.getId();
  }

  public String getAnswerName() {
    return answerEntity.getAnswerName();
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
    AnswerDTO answerDTO = (AnswerDTO) o;
    return answerEntity.equals(answerDTO.answerEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), answerEntity);
  }
}
