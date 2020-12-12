package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.GenderEntity;
import com.didula.dto.GenderDTO;
import com.didula.service.implementation.GenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenderController implements ControllerWithDTO<GenderDTO, GenderEntity> {
  private final GenderService genderService;

  public GenderController(GenderService genderService) {
    this.genderService = genderService;
  }

  @GetMapping(value = "/genders")
  public ResponseEntity<List<GenderDTO>> getGenders() {
    List<GenderEntity> genderEntities = genderService.getAll();
    List<GenderDTO> genderDTOs = createDTOs(genderEntities);
    return new ResponseEntity<>(genderDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/genders/{genderId}")
  public ResponseEntity<GenderDTO> getGender(@PathVariable Integer genderId) {
    GenderEntity gender = genderService.getById(genderId);
    GenderDTO genderDTO = createDTO(gender);
    return new ResponseEntity<>(genderDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/genders")
  public ResponseEntity<GenderDTO> createGender(@RequestBody GenderEntity genderEntity) {
    GenderEntity newGender = genderService.create(genderEntity);
    GenderDTO genderDTO = createDTO(newGender);
    return new ResponseEntity<>(genderDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/genders/{genderId}")
  public ResponseEntity<GenderDTO> updateGender(@RequestBody GenderEntity genderEntity, @PathVariable Integer genderId) {
    GenderEntity newGender = genderService.update(genderEntity, genderId);
    GenderDTO genderDTO = createDTO(newGender);
    return new ResponseEntity<>(genderDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/genders/{genderId}")
  public ResponseEntity<Void> deleteGender(@PathVariable Integer genderId) {
    genderService.delete(genderId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<GenderDTO> createDTOs(Iterable<GenderEntity> genderEntities) {
    List<GenderDTO> genderDTOs = new ArrayList<>();
    for (GenderEntity genderEntity : genderEntities) {
      GenderDTO genderDTO = new GenderDTO(genderEntity);
      genderDTOs.add(genderDTO);
    }
    return genderDTOs;
  }

  @Override
  public GenderDTO createDTO(GenderEntity genderEntity) {
    return new GenderDTO(genderEntity);
  }
}
