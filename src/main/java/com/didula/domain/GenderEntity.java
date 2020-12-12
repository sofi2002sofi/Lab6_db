package com.didula.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gender", schema = "lab_5_db")
public class GenderEntity {
  private Integer id;
  private String genderName;
  private Set<UserEntity> users;

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
  @Column(name = "gender_name")
  public String getGenderName() {
    return genderName;
  }

  public void setGenderName(String genderName) {
    this.genderName = genderName;
  }

  public GenderEntity() {

  }

  public GenderEntity(Integer id, String genderName) {
    this.id = id;
    this.genderName = genderName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GenderEntity that = (GenderEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (genderName != null ? !genderName.equals(that.genderName) : that.genderName != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (genderName != null ? genderName.hashCode() : 0);
    return result;
  }

  @OneToMany(mappedBy = "genderByGenderId")
  public Set<UserEntity> getUsers() {
    return users;
  }

  public void setUsers(Set<UserEntity> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return  String.format("%3s   %15s ", id, genderName);
  }

}
