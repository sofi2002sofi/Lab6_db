package com.didula.domain;

import javax.persistence.*;

@Entity
@Table(name = "test", schema = "lab_5_db")
public class TestEntity {
  private Integer id;
  private String testName;
  private String deadlineDatetime;
  private Byte completed;
  private Byte overdue;
  private CourseInfoEntity courseInfoByCourseInfoId;

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
  @Column(name = "test_name")
  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  @Basic
  @Column(name = "deadline_datetime")
  public String getDeadlineDatetime() {
    return deadlineDatetime;
  }

  public void setDeadlineDatetime(String deadlineDatetime) {
    this.deadlineDatetime = deadlineDatetime;
  }

  @Basic
  @Column(name = "completed")
  public Byte getCompleted() {
    return completed;
  }

  public void setCompleted(Byte completed) {
    this.completed = completed;
  }

  @Basic
  @Column(name = "overdue")
  public Byte getOverdue() {
    return overdue;
  }

  public void setOverdue(Byte overdue) {
    this.overdue = overdue;
  }

  public TestEntity() {
  }

  public TestEntity(Integer id, String testName, String deadlineDatetime, Byte completed,
                    Byte overdue, CourseInfoEntity courseInfoByCourseInfoId) {
    this.id = id;
    this.testName = testName;
    this.deadlineDatetime = deadlineDatetime;
    this.completed = completed;
    this.overdue = overdue;
    this.courseInfoByCourseInfoId = courseInfoByCourseInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TestEntity that = (TestEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (testName != null ? !testName.equals(that.testName) : that.testName != null) {
      return false;
    }
    if (deadlineDatetime != null ? !deadlineDatetime.equals(that.deadlineDatetime) :
            that.deadlineDatetime != null) {
      return false;
    }
    if (completed != null ? !completed.equals(that.completed) : that.completed != null) {
      return false;
    }
    if (overdue != null ? !overdue.equals(that.overdue) : that.overdue != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (testName != null ? testName.hashCode() : 0);
    result = 31 * result + (deadlineDatetime != null ? deadlineDatetime.hashCode() : 0);
    result = 31 * result + (completed != null ? completed.hashCode() : 0);
    result = 31 * result + (overdue != null ? overdue.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "course_info_id", referencedColumnName = "id", nullable = false)
  public CourseInfoEntity getCourseInfoByCourseInfoId() {
    return courseInfoByCourseInfoId;
  }

  public void setCourseInfoByCourseInfoId(CourseInfoEntity courseInfoByCourseInfoId) {
    this.courseInfoByCourseInfoId = courseInfoByCourseInfoId;
  }

  @Override
  public String toString() {
    return String.format("%3s   %50s   %20s   %10s   %10s   %3s", id, testName, deadlineDatetime,
            completed, overdue, courseInfoByCourseInfoId.getId());
  }

}
