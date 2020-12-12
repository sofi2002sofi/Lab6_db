package com.didula.domain;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "lab_5_db")
public class MessageEntity {
  private Integer id;
  private String messageContent;
  private Integer countOfOverduedTests;
  private Integer countOfOverduedModules;
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
  @Column(name = "message_content")
  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

  @Basic
  @Column(name = "count_of_overdued_tests")
  public Integer getCountOfOverduedTests() {
    return countOfOverduedTests;
  }

  public void setCountOfOverduedTests(Integer countOfOverduedTests) {
    this.countOfOverduedTests = countOfOverduedTests;
  }

  @Basic
  @Column(name = "count_of_overdued_modules")
  public Integer getCountOfOverduedModules() {
    return countOfOverduedModules;
  }

  public void setCountOfOverduedModules(Integer countOfOverduedModules) {
    this.countOfOverduedModules = countOfOverduedModules;
  }

  public MessageEntity() {

  }

  public MessageEntity(Integer id, String messageContent, Integer countOfOverduedTests,
                       Integer countOfOverduedModules, CourseInfoEntity courseInfoByCourseInfoId) {
    this.id = id;
    this.messageContent = messageContent;
    this.countOfOverduedTests = countOfOverduedTests;
    this.countOfOverduedModules = countOfOverduedModules;
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

    MessageEntity that = (MessageEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (messageContent != null ? !messageContent.equals(that.messageContent) :
            that.messageContent != null) {
      return false;
    }
    if (countOfOverduedTests != null ? !countOfOverduedTests.equals(that.countOfOverduedTests) :
            that.countOfOverduedTests != null) {
      return false;
    }
    if (countOfOverduedModules != null ? !countOfOverduedModules.equals(that.countOfOverduedModules) :
            that.countOfOverduedModules != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
    result = 31 * result + (countOfOverduedTests != null ? countOfOverduedTests.hashCode() : 0);
    result = 31 * result + (countOfOverduedModules != null ? countOfOverduedModules.hashCode() : 0);
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
    return  String.format("%3s   %70s   %20s   %20s   %10s ", id, messageContent,
            countOfOverduedTests, countOfOverduedModules, courseInfoByCourseInfoId.getId());
  }

}
