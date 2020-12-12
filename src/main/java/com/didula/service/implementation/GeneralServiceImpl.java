package com.didula.service.implementation;

import com.didula.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class GeneralServiceImpl<T, ID> implements GeneralService<T, ID> {
  protected abstract JpaRepository<T, ID> getRepository();

  protected abstract void throwException();

  protected abstract T mergeEntities(T newEntity, T entity);

  @Override
  public List<T> getAll() {
    return getRepository().findAll();
  }

  @Override
  public T getById(ID id) {
    if(getRepository().existsById(id)){
      return getRepository().findById(id).get();
    }
    throwException();
    return null;
  }

  @Override
  public T create(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T update(T entity, ID id) {
    if(getRepository().existsById(id)){
      T newEntity = getRepository().findById(id).get();
      return getRepository().save(mergeEntities(newEntity, entity));
    }
    throwException();
    return null;
  }

  @Override
  public void delete(ID id) {
    if(getRepository().existsById(id)){
      getRepository().deleteById(id);
      return;
    }
    throwException();
  }
}
