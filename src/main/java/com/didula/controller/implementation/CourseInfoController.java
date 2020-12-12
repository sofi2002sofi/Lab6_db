package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.CourseInfoEntity;
import com.didula.dto.CourseInfoDTO;
import com.didula.service.implementation.CourseInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CourseInfoController implements ControllerWithDTO<CourseInfoDTO, CourseInfoEntity> {
  private final CourseInfoService courseInfoService;

  public CourseInfoController(CourseInfoService courseInfoService) {
    this.courseInfoService = courseInfoService;
  }

  @GetMapping(value = "/coursesInfo")
  public ResponseEntity<List<CourseInfoDTO>> getCoursesInfo() {
    List<CourseInfoEntity> courseInfoEntities = courseInfoService.getAll();
    List<CourseInfoDTO> courseInfoDTOs = createDTOs(courseInfoEntities);
    return new ResponseEntity<>(courseInfoDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/coursesInfo/{courseInfoId}")
  public ResponseEntity<CourseInfoDTO> getCourseInfo(@PathVariable Integer courseInfoId) {
    CourseInfoEntity courseInfoEntity = courseInfoService.getById(courseInfoId);
    CourseInfoDTO courseInfoDTO = createDTO(courseInfoEntity);
    return new ResponseEntity<>(courseInfoDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/coursesInfo")
  public ResponseEntity<CourseInfoDTO> createCourseInfo(@RequestBody CourseInfoEntity courseInfoEntity) {
    CourseInfoEntity newCourseInfo = courseInfoService.create(courseInfoEntity);
    CourseInfoDTO courseInfoDTO = createDTO(newCourseInfo);
    return new ResponseEntity<>(courseInfoDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/coursesInfo/{courseInfoId}")
  public ResponseEntity<CourseInfoDTO> updateCourseInfo(@RequestBody CourseInfoEntity courseInfoEntity, @PathVariable Integer courseInfoId) {
    CourseInfoEntity newCourseInfo = courseInfoService.update(courseInfoEntity, courseInfoId);
    CourseInfoDTO courseInfoDTO = createDTO(newCourseInfo);
    return new ResponseEntity<>(courseInfoDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/coursesInfo/{courseInfoId}")
  public ResponseEntity<Void> deleteCourseInfo(@PathVariable Integer courseInfoId) {
    courseInfoService.delete(courseInfoId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<CourseInfoDTO> createDTOs(Iterable<CourseInfoEntity> courseInfoEntities) {
    List<CourseInfoDTO> courseInfoDTOs = new ArrayList<>();
    for (CourseInfoEntity courseInfoEntity : courseInfoEntities) {
      CourseInfoDTO courseInfoDTO = new CourseInfoDTO(courseInfoEntity);
      courseInfoDTOs.add(courseInfoDTO);
    }
    return courseInfoDTOs;
  }

  @GetMapping(value = "/coursesInfo/courses/{courseId}")
  public ResponseEntity<List<CourseInfoDTO>> getCoursesInfoByCourseId(
          @PathVariable Integer courseId) {
    Set<CourseInfoEntity> courseInfoEntities =
            courseInfoService.getCoursesInfoByCourseId(courseId);
    List<CourseInfoDTO> courseInfoDTOs = createDTOs(courseInfoEntities);

    return new ResponseEntity<>(courseInfoDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/coursesInfo/users/{userId}")
  public ResponseEntity<List<CourseInfoDTO>> getCoursesInfoByUserId(
          @PathVariable Integer userId) {
    Set<CourseInfoEntity> courseInfoEntities =
            courseInfoService.getCoursesInfoByUserId(userId);
    List<CourseInfoDTO> courseInfoDTOs = createDTOs(courseInfoEntities);

    return new ResponseEntity<>(courseInfoDTOs, HttpStatus.OK);
  }

  @Override
  public CourseInfoDTO createDTO(CourseInfoEntity courseInfoEntity) {
    return new CourseInfoDTO(courseInfoEntity);
  }
}
