package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.CourseEntity;
import com.didula.dto.CourseDTO;
import com.didula.service.implementation.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController implements ControllerWithDTO<CourseDTO, CourseEntity> {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping(value = "/courses")
  public ResponseEntity<List<CourseDTO>> getCourses() {
    List<CourseEntity> courseEntities = courseService.getAll();
    List<CourseDTO> courseDTOs = createDTOs(courseEntities);
    return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/courses/{courseId}")
  public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer courseId) {
    CourseEntity courseEntity = courseService.getById(courseId);
    CourseDTO courseDTO = createDTO(courseEntity);
    return new ResponseEntity<>(courseDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/courses")
  public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseEntity courseEntity) {
    CourseEntity newCourse = courseService.create(courseEntity);
    CourseDTO courseDTO = createDTO(newCourse);
    return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/courses/{courseId}")
  public ResponseEntity<CourseDTO> updateGender(@RequestBody CourseEntity courseEntity, @PathVariable Integer courseId) {
    CourseEntity newCourse = courseService.update(courseEntity, courseId);
    CourseDTO courseDTO = createDTO(newCourse);
    return new ResponseEntity<>(courseDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/courses/{courseId}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Integer courseId) {
    courseService.delete(courseId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<CourseDTO> createDTOs(Iterable<CourseEntity> courseEntities) {
    List<CourseDTO> courseDTOs = new ArrayList<>();
    for (CourseEntity courseEntity : courseEntities) {
      CourseDTO courseDTO = new CourseDTO(courseEntity);
      courseDTOs.add(courseDTO);
    }
    return courseDTOs;
  }

  @Override
  public CourseDTO createDTO(CourseEntity courseEntity) {
    return new CourseDTO(courseEntity);
  }
}
