package com.didula.controller;

import java.util.List;

public interface ControllerWithDTO<T, E> {
  List<T> createDTOs(Iterable<E> entities);

  T createDTO(E entity);
}
