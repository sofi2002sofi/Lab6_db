package com.didula.dto;

import java.util.Objects;

public class MessageDTO {
  private String message;

  public MessageDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDTO that = (MessageDTO) o;
    return message.equals(that.message);
  }

  @Override
  public int hashCode() {
    return message != null ? message.hashCode() : 0;
  }
}
