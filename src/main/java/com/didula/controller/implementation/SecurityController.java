package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.SecurityEntity;
import com.didula.dto.SecurityDTO;
import com.didula.service.implementation.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SecurityController implements ControllerWithDTO<SecurityDTO, SecurityEntity> {
  private final SecurityService securityService;

  public SecurityController(SecurityService securityService) {
    this.securityService = securityService;
  }

  @GetMapping(value = "/securities")
  public ResponseEntity<List<SecurityDTO>> getSecurities() {
    List<SecurityEntity> securityEntities = securityService.getAll();
    List<SecurityDTO> securityDTOs = createDTOs(securityEntities);
    return new ResponseEntity<>(securityDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/securities/{securityId}")
  public ResponseEntity<SecurityDTO> getSecurity(@PathVariable Integer securityId) {
    SecurityEntity securityEntity = securityService.getById(securityId);
    SecurityDTO securityDTO = createDTO(securityEntity);
    return new ResponseEntity<>(securityDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/securities")
  public ResponseEntity<SecurityDTO> createSecurity(@RequestBody SecurityEntity securityEntity) {
    SecurityEntity newSecurity = securityService.create(securityEntity);
    SecurityDTO securityDTO = createDTO(newSecurity);
    return new ResponseEntity<>(securityDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/securities/{securityId}")
  public ResponseEntity<SecurityDTO> updateSecurity(@RequestBody SecurityEntity securityEntity, @PathVariable Integer securityId) {
    SecurityEntity newSecurity = securityService.update(securityEntity, securityId);
    SecurityDTO securityDTO = createDTO(newSecurity);
    return new ResponseEntity<>(securityDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/securities/{securityId}")
  public ResponseEntity<Void> deleteSecurity(@PathVariable Integer securityId) {
    securityService.delete(securityId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<SecurityDTO> createDTOs(Iterable<SecurityEntity> securityEntities) {
    List<SecurityDTO> securityDTOs = new ArrayList<>();
    for (SecurityEntity securityEntity : securityEntities) {
      SecurityDTO securityDTO = new SecurityDTO(securityEntity);
      securityDTOs.add(securityDTO);
    }
    return securityDTOs;
  }

  @GetMapping(value = "/securities/users/{userId}")
  public ResponseEntity<SecurityDTO> getSecurityByUserId(
          @PathVariable Integer userId) {
    SecurityEntity securityEntity =
            securityService.getSecurityByUserId(userId);
    SecurityDTO securityDTO = createDTO(securityEntity);

    return new ResponseEntity<>(securityDTO, HttpStatus.OK);
  }

  @Override
  public SecurityDTO createDTO(SecurityEntity securityEntity) {
    return new SecurityDTO(securityEntity);
  }
}
