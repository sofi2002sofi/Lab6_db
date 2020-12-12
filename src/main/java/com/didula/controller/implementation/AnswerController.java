package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.AnswerEntity;
import com.didula.dto.AnswerDTO;
import com.didula.service.implementation.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnswerController implements ControllerWithDTO<AnswerDTO, AnswerEntity> {
  private final AnswerService answerService;

  public AnswerController(AnswerService answerService) {
    this.answerService = answerService;
  }

  @GetMapping(value = "/answers")
  public ResponseEntity<List<AnswerDTO>> getAnswers() {
    List<AnswerEntity> answerEntities = answerService.getAll();
    List<AnswerDTO> answerDTOs = createDTOs(answerEntities);
    return new ResponseEntity<>(answerDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/answers/{answerId}")
  public ResponseEntity<AnswerDTO> getAnswer(@PathVariable Integer answerId) {
    AnswerEntity answerEntity = answerService.getById(answerId);
    AnswerDTO answerDTO = createDTO(answerEntity);
    return new ResponseEntity<>(answerDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/answers")
  public ResponseEntity<AnswerDTO> createAnswer(@RequestBody AnswerEntity answerEntity) {
    AnswerEntity newAnswer = answerService.create(answerEntity);
    AnswerDTO answerDTO = createDTO(newAnswer);
    return new ResponseEntity<>(answerDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/answers/{answerId}")
  public ResponseEntity<AnswerDTO> updateAnswer(@RequestBody AnswerEntity answerEntity, @PathVariable Integer answerId) {
    AnswerEntity newAnswer = answerService.update(answerEntity, answerId);
    AnswerDTO answerDTO = createDTO(newAnswer);
    return new ResponseEntity<>(answerDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/answers/{answerId}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable Integer answerId) {
    answerService.delete(answerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<AnswerDTO> createDTOs(Iterable<AnswerEntity> answerEntities) {
    List<AnswerDTO> answerDTOs = new ArrayList<>();
    for (AnswerEntity answerEntity : answerEntities) {
      AnswerDTO answerDTO = new AnswerDTO(answerEntity);
      answerDTOs.add(answerDTO);
    }
    return answerDTOs;
  }

  @Override
  public AnswerDTO createDTO(AnswerEntity answerEntity) {
    return new AnswerDTO(answerEntity);
  }
}
