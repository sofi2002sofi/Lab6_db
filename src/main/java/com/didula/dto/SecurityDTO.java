package com.didula.dto;

import com.didula.controller.implementation.SecurityController;
import com.didula.controller.implementation.UserController;
import com.didula.domain.SecurityEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SecurityDTO extends ResourceSupport {
  private final SecurityEntity securityEntity;

  public SecurityDTO(SecurityEntity securityEntity) {
    this.securityEntity = securityEntity;

    add(linkTo(methodOn(SecurityController.class).getSecurity(securityEntity.getId())).withSelfRel());
    add(linkTo(methodOn(UserController.class).getUser(securityEntity.getUserByUserId().getId())).withRel("user"));
  }

  public Integer getSecurityId() {
    return securityEntity.getId();
  }

  public String getPassword() {
    return securityEntity.getPassword();
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
    SecurityDTO that = (SecurityDTO) o;
    return Objects.equals(securityEntity, that.securityEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), securityEntity);
  }
}
