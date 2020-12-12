package com.didula.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course", schema = "lab_5_db")
public class CourseEntity {
  private Integer id;
  private String courseName;
  private Double durationInHours;
  private Set<com.didula.domain.CourseInfoEntity> coursesInfo;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "course_name")
  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  @Basic
  @Column(name = "duration_in_hours")
  public Double getDurationInHours() {
    return durationInHours;
  }

  public void setDurationInHours(Double durationInHours) {
    this.durationInHours = durationInHours;
  }

  public CourseEntity() {

  }

  public CourseEntity(Integer id, String courseName, Double durationInHours) {
    this.id = id;
    this.courseName = courseName;
    this.durationInHours = durationInHours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CourseEntity that = (CourseEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) {
      return false;
    }
    if (durationInHours != null ? !durationInHours.equals(that.durationInHours) :
            that.durationInHours != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
    result = 31 * result + (durationInHours != null ? durationInHours.hashCode() : 0);
    return result;
  }

  @OneToMany(mappedBy = "courseByCourseId")
  public Set<com.didula.domain.CourseInfoEntity> getCoursesInfo() {
    return coursesInfo;
  }

  public void setCoursesInfo(Set<com.didula.domain.CourseInfoEntity> coursesInfo) {
    this.coursesInfo = coursesInfo;
  }


  @Override
  public String toString() {
    return  String.format("%3s   %30s   %15s ", id, courseName, durationInHours);
  }

}
