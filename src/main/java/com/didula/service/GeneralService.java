package com.didula.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface GeneralService<T, ID> {

  public List<T> getAll();

  public T getById(ID id);

  @Transactional
  public T create(T entity);

  @Transactional
  public T update(T entity, ID id);

  @Transactional
  public void delete(ID id);
}
