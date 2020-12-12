package com.didula.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "lab_5_db")
public class UserEntity {
  private Integer id;
  private String fullName;
  private String forumsName;
  private String email;
  private Integer birthYear;
  private GenderEntity genderByGenderId;
  private Set<CourseInfoEntity> coursesInfo;
  private com.didula.domain.SecurityEntity securityBySecurityId;

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
  @Column(name = "full_name")
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Basic
  @Column(name = "forums_name")
  public String getForumsName() {
    return forumsName;
  }

  public void setForumsName(String forumsName) {
    this.forumsName = forumsName;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "birth_year", nullable = true)
  public Integer getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(Integer birthYear) {
    this.birthYear = birthYear;
  }

  public UserEntity() {}

  public UserEntity(
          Integer id,
          String fullName,
          String forumsName,
          String email,
          Integer birthYear,
          GenderEntity genderByGenderId) {
    this.id = id;
    this.fullName = fullName;
    this.forumsName = forumsName;
    this.email = email;
    this.birthYear = birthYear;
    this.genderByGenderId = genderByGenderId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserEntity that = (UserEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) {
      return false;
    }
    if (forumsName != null ? !forumsName.equals(that.forumsName) : that.forumsName != null) {
      return false;
    }
    if (email != null ? !email.equals(that.email) : that.email != null) {
      return false;
    }
    if (birthYear != null ? !birthYear.equals(that.birthYear) : that.birthYear != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    result = 31 * result + (forumsName != null ? forumsName.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = true)
  public GenderEntity getGenderByGenderId() {
    return genderByGenderId;
  }

  public void setGenderByGenderId(GenderEntity genderByGenderId) {
    this.genderByGenderId = genderByGenderId;
  }

  @OneToMany(mappedBy = "userByUserId")
  public Set<CourseInfoEntity> getCoursesInfo() {
    return coursesInfo;
  }

  public void setCoursesInfo(Set<CourseInfoEntity> coursesInfo) {
    this.coursesInfo = coursesInfo;
  }

  @OneToOne(mappedBy = "userByUserId")
  public com.didula.domain.SecurityEntity getSecurityBySecurityId() {
    return securityBySecurityId;
  }

  public void setSecurityBySecurityId(com.didula.domain.SecurityEntity securityBySecurityId) {
    this.securityBySecurityId = securityBySecurityId;
  }

  @Override
  public String toString() {
    return  String.format("%3s   %50s   %50s   %30s   %4s   %6s ", id, fullName, forumsName,
            email, birthYear, genderByGenderId.getId());
  }

}
