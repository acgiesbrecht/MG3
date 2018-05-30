package com.gnadenheimer.mg3.model.dao;

import java.util.List;

public interface AbstractDao<T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    List<T> findAll();

    List<T> getListFromQuery(String jpqlQuery);

}
