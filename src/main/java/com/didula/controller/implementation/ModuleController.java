package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.ModuleEntity;
import com.didula.dto.ModuleDTO;
import com.didula.service.implementation.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ModuleController implements ControllerWithDTO<ModuleDTO, ModuleEntity> {
  private final ModuleService moduleService;

  public ModuleController(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @GetMapping(value = "/modules")
  public ResponseEntity<List<ModuleDTO>> getModules() {
    List<ModuleEntity> moduleEntities = moduleService.getAll();
    List<ModuleDTO> moduleDTOs = createDTOs(moduleEntities);
    return new ResponseEntity<>(moduleDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/modules/{moduleId}")
  public ResponseEntity<ModuleDTO> getModule(@PathVariable Integer moduleId) {
    ModuleEntity moduleEntity = moduleService.getById(moduleId);
    ModuleDTO moduleDTO = createDTO(moduleEntity);
    return new ResponseEntity<>(moduleDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/modules")
  public ResponseEntity<ModuleDTO> createModule(@RequestBody ModuleEntity moduleEntity){
    ModuleEntity newModule = moduleService.create(moduleEntity);
    ModuleDTO moduleDTO = createDTO(newModule);
    return new ResponseEntity<>(moduleDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/modules/{moduleId}")
  public ResponseEntity<ModuleDTO> updateModule(@RequestBody ModuleEntity moduleEntity, @PathVariable Integer moduleId) {
    ModuleEntity newModule = moduleService.update(moduleEntity, moduleId);
    ModuleDTO moduleDTO = createDTO(newModule);
    return new ResponseEntity<>(moduleDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/modules/{moduleId}")
  public ResponseEntity<Void> deleteModule(@PathVariable Integer moduleId) {
    moduleService.delete(moduleId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<ModuleDTO> createDTOs(Iterable<ModuleEntity> moduleEntities) {
    List<ModuleDTO> moduleDTOs = new ArrayList<>();
    for (ModuleEntity moduleEntity : moduleEntities) {
      ModuleDTO moduleDTO = new ModuleDTO(moduleEntity);
      moduleDTOs.add(moduleDTO);
    }
    return moduleDTOs;
  }

  @GetMapping(value = "/modules/coursesInfo/{courseInfoId}")
  public ResponseEntity<List<ModuleDTO>> getTestByCourseInfoId(
          @PathVariable Integer courseInfoId) {
    Set<ModuleEntity> moduleEntities =
            moduleService.getModuleByCourseInfoId(courseInfoId);
    List<ModuleDTO> moduleDTOs = createDTOs(moduleEntities);

    return new ResponseEntity<>(moduleDTOs, HttpStatus.OK);
  }

  @Override
  public ModuleDTO createDTO(ModuleEntity moduleEntity) {
    return new ModuleDTO(moduleEntity);
  }
}
