package com.didula.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "course_info", schema = "lab_5_db")
public class CourseInfoEntity {
  private Integer id;
  private String startDate;
  private BigDecimal completionInPercents;
  private Byte availableMessage;
  private CourseEntity courseByCourseId;
  private UserEntity userByUserId;
  private Set<ModuleEntity> modulesEntity;
  private Set<TestEntity> testsEntity;
  private Set<MessageEntity> messagesEntity;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "start_date", nullable = false)
  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @Basic
  @Column(name = "completion_in_percents", nullable = false)
  public BigDecimal getCompletionInPercents() {
    return completionInPercents;
  }

  public void setCompletionInPercents(BigDecimal completionInPercents) {
    this.completionInPercents = completionInPercents;
  }

  @Basic
  @Column(name = "available_message", nullable = false)
  public Byte getAvailableMessage() {
    return availableMessage;
  }

  public void setAvailableMessage(Byte availableMessage) {
    this.availableMessage = availableMessage;
  }

  public CourseInfoEntity() {

  }

  public CourseInfoEntity(Integer id, String startDate, BigDecimal completionInPercents, Byte availableMessage,
                          CourseEntity courseByCourseId, UserEntity userByUserId) {
    this.id = id;
    this.startDate = startDate;
    this.completionInPercents = completionInPercents;
    this.availableMessage = availableMessage;
    this.courseByCourseId = courseByCourseId;
    this.userByUserId = userByUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CourseInfoEntity that = (CourseInfoEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) {
      return false;
    }
    if (completionInPercents != null ? !completionInPercents.equals(that.completionInPercents) :
            that.completionInPercents != null) {
      return false;
    }
    if (availableMessage != null ? !availableMessage.equals(that.availableMessage) :
            that.availableMessage != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
    result = 31 * result + (completionInPercents != null ? completionInPercents.hashCode() : 0);
    result = 31 * result + (availableMessage != null ? availableMessage.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
  public CourseEntity getCourseByCourseId() {
    return courseByCourseId;
  }

  public void setCourseByCourseId(CourseEntity courseByCourseId) {
    this.courseByCourseId = courseByCourseId;
  }

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  public UserEntity getUserByUserId() {
  return userByUserId;
  }

  public void setUserByUserId(UserEntity userByUserId) {
    this.userByUserId = userByUserId;
  }

  @OneToMany(mappedBy = "courseInfoByCourseInfoId")
  public Set<ModuleEntity> getModulesEntity() {
    return modulesEntity;
  }

  public void setModulesEntity(Set<ModuleEntity> modulesEntity) {
    this.modulesEntity = modulesEntity;
  }

  @OneToMany(mappedBy = "courseInfoByCourseInfoId")
  public Set<TestEntity> getTestsEntity() {
    return testsEntity;
  }

  public void setTestsEntity(Set<TestEntity> testsEntity) {
    this.testsEntity = testsEntity;
  }

  @OneToMany(mappedBy = "courseInfoByCourseInfoId")
  public Set<MessageEntity> getMessagesEntity() {
    return messagesEntity;
  }

  public void setMessagesEntity(Set<MessageEntity> messagesEntity) {
    this.messagesEntity = messagesEntity;
  }

  @Override
  public String toString() {
    return  String.format("%3s   %12s   %16s   %16s   %10s  %8s", id, startDate,
            completionInPercents, availableMessage, courseByCourseId.getId(), userByUserId.getId());
  }

}
