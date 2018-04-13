package com.nhsoft.test;

import com.nhsoft.test.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDaoImpl<T>implements BaseDao<T> {


    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        clazz =(Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) {
        System.out.println(entity);
    }

    @Override
    public void update(T entity) {
        System.out.println(entity);
    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
