package com.nhsoft.test;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(Serializable id);

    public List<T> findAll();
}
