package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.TestEntity;
import com.didula.dto.TestDTO;
import com.didula.service.implementation.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class TestController implements ControllerWithDTO<TestDTO, TestEntity> {
  private final TestService testService;

  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping(value = "/tests")
  public ResponseEntity<List<TestDTO>> getTests() {
    List<TestEntity> testEntities = testService.getAll();
    List<TestDTO> testDTOs = createDTOs(testEntities);
    return new ResponseEntity<>(testDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/tests/{testId}")
  public ResponseEntity<TestDTO> getTest(@PathVariable Integer testId) {
    TestEntity testEntity = testService.getById(testId);
    TestDTO testDTO = createDTO(testEntity);
    return new ResponseEntity<>(testDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/tests")
  public ResponseEntity<TestDTO> createTest(@RequestBody TestEntity testEntity) {
    TestEntity newTest = testService.create(testEntity);
    TestDTO testDTO = createDTO(newTest);
    return new ResponseEntity<>(testDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/tests/{testId}")
  public ResponseEntity<TestDTO> updateTest(@RequestBody TestEntity testEntity, @PathVariable Integer testId) {
    TestEntity newTest = testService.update(testEntity, testId);
    TestDTO testDTO = createDTO(newTest);
    return new ResponseEntity<>(testDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/tests/{testId}")
  public ResponseEntity<Void> deleteTest(@PathVariable Integer testId) {
    testService.delete(testId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<TestDTO> createDTOs(Iterable<TestEntity> testEntities) {
    List<TestDTO> testDTOs = new ArrayList<>();
    for (TestEntity testEntity : testEntities) {
      TestDTO testDTO = new TestDTO(testEntity);
      testDTOs.add(testDTO);
    }
    return testDTOs;
  }

  @GetMapping(value = "/tests/coursesInfo/{courseInfoId}")
  public ResponseEntity<List<TestDTO>> getTestByCourseInfoId(
          @PathVariable Integer courseInfoId) {
    Set<TestEntity> testEntities =
            testService.getTestByCourseInfoId(courseInfoId);
    List<TestDTO> testDTOs = createDTOs(testEntities);

    return new ResponseEntity<>(testDTOs, HttpStatus.OK);
  }

  @Override
  public TestDTO createDTO(TestEntity testEntity) {
    return new TestDTO(testEntity);
  }
}
