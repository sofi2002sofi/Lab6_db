package com.didula.dto;

import com.didula.controller.implementation.GenderController;
import com.didula.domain.GenderEntity;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GenderDTO extends ResourceSupport {

  private final GenderEntity genderEntity;

  public GenderDTO(GenderEntity genderEntity) {
    this.genderEntity = genderEntity;

    add(linkTo(methodOn(GenderController.class).getGender(genderEntity.getId())).withSelfRel());
  }

  public Integer getGenderId() {
    return genderEntity.getId();
  }

  public String getGenderName() {
    return genderEntity.getGenderName();
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
    GenderDTO genderDTO = (GenderDTO) o;
    return genderEntity.equals(genderDTO.genderEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), genderEntity);
  }
}
