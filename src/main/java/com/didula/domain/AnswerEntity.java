package com.didula.domain;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "lab_5_db")
public class AnswerEntity {
  private Integer id;
  private String answerName;

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
  @Column(name = "answer_name")
  public String getAnswerName() {
    return answerName;
  }

  public void setAnswerName(String answerName) {
    this.answerName = answerName;
  }

  public AnswerEntity() {

  }

  public AnswerEntity(Integer id, String answerName) {
    this.id = id;
    this.answerName = answerName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AnswerEntity that = (AnswerEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (answerName != null ? !answerName.equals(that.answerName) : that.answerName != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (answerName != null ? answerName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return  String.format("%3s   %30s ", id, answerName);
  }

}
