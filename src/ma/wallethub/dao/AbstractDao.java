/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao;

import java.util.List;

/**
 *
 * @author YounesSama
 */
public interface AbstractDao<T> {

    int count();

    void create(T entity);

    void create(List<T> entities);

    void edit(T entity);

    int exec(String nativeQuery);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int[] range);

    Long generateId(String beanName, String idName);

    void remove(T entity);

    List<T> selectMultiple(String nativeQuery);

    T selectSingle(String nativeQuery);

    int clear();

    public void createAndClear(List<T> entities);

}
