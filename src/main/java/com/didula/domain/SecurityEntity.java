package com.didula.domain;

import javax.persistence.*;

@Entity
@Table(name = "security", schema = "lab_5_db")
public class SecurityEntity {
  private Integer id;
  private String password;
  private UserEntity userByUserId;

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
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SecurityEntity() {

  }

  public SecurityEntity(Integer id, String password, UserEntity userByUserId) {
    this.id = id;
    this.password = password;
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

    SecurityEntity that = (SecurityEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (password != null ? !password.equals(that.password) : that.password != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  @OneToOne(fetch= FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  public UserEntity getUserByUserId() {
    return userByUserId;
  }

  public void setUserByUserId(UserEntity userByUserId) {
    this.userByUserId = userByUserId;
  }

  @Override
  public String toString() {
    return  String.format("%3s   %8s   %3s ", id, password, userByUserId.getId());
  }

}
