/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Younes
 */
public class AbstractDaoImpl<T> {

    private static final String PERSISTENCE_UNIT_NAME = "parserPU";
    private Class<T> entityClass;
    private EntityManager em;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        if (em == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            em = entityManagerFactory.createEntityManager();
        }
        return em;
    }

    public List<T> selectMultiple(String nativeQuery) {
        return getEntityManager().createQuery(nativeQuery).getResultList();
    }

    public T selectSingle(String nativeQuery) {
        List<T> list = selectMultiple(nativeQuery);
        if (list == null || list.isEmpty() || list.size() > 1) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public int exec(String nativeQuery) {
        getEntityManager().getTransaction().begin();
        int res = getEntityManager().createQuery(nativeQuery).executeUpdate();
        getEntityManager().getTransaction().commit();
        return res;
    }

    public int clear() {
        return exec("DELETE FROM " + entityClass.getSimpleName());
    }

    public void create(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }

    public void create(List<T> entities) {
        getEntityManager().getTransaction().begin();
        entities.forEach(e -> getEntityManager().persist(e));
        getEntityManager().getTransaction().commit();
    }

    public void createAndClear(List<T> entities) {
      clear();
        create(entities);
    }

    public void edit(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

    }

    public void remove(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().getTransaction().commit();
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public Long generateId(String beanName, String idName) {
        List<Long> maxId = getEntityManager().createQuery(" Select max(item." + idName + ") FROM " + beanName + " item").getResultList();
        if (maxId == null || maxId.isEmpty() || maxId.get(0) == null) {
            return 1L;
        }
        return maxId.get(0) + 1;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
