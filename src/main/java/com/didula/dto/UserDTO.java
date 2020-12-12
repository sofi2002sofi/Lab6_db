package com.didula.dto;

import com.didula.controller.implementation.GenderController;
import com.didula.controller.implementation.UserController;
import com.didula.domain.UserEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserDTO extends ResourceSupport {
  private final UserEntity userEntity;

  public UserDTO(UserEntity userEntity) {
    this.userEntity = userEntity;

    add(linkTo(methodOn(UserController.class).getUser(userEntity.getId())).withSelfRel());
    add(linkTo(methodOn(GenderController.class).getGender(userEntity.getGenderByGenderId().getId())).withRel("gender"));
  }

  public Integer getUserId() {
    return userEntity.getId();
  }

  public String getFullName() {
    return userEntity.getFullName();
  }

  public String getForumsName() {
    return userEntity.getForumsName();
  }

  public String getEmail() {
    return userEntity.getEmail();
  }

  public Integer getBirthYear() {
    return userEntity.getBirthYear();
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
    UserDTO userDTO = (UserDTO) o;
    return Objects.equals(userEntity, userDTO.userEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), userEntity);
  }
}
