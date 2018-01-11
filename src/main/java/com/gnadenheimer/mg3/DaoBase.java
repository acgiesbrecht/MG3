/*
 * License BSD License
 * Copyright (C) 2013 Amrullah <amrullah@panemu.com>.
 */
package com.gnadenheimer.mg3;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import javafx.scene.control.TableColumn.SortType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class DaoBase<T> {

    private Class<T> voClass;

    @Inject
    EntityManager em;

    public DaoBase(Class<T> clazz) {
//        this.em = em;
        this.voClass = clazz;
    }

    public List<T> getList() {
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = builder.createQuery(voClass);

            Root<T> root = cq.from(voClass);
            cq.select(root);

            TypedQuery<T> typedQuery = em.createQuery(cq);

            List<T> result = typedQuery.getResultList();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<T> getList(String query) {
        try {
            Query q = em.createQuery(query);
            //Derby no soporta boolean por defecto
            if (query.contains("trueValue")) {
                q.setParameter("trueValue", true);
            }
            List<T> result = (List<T>) q.getResultList();

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public TableData fetch(int startIndex,
            List<TableCriteria> filteredColumns,
            List<String> sortedColumns,
            List<SortType> sortingVersus,
            int maxResult) {
        try {
            return this.fetch(startIndex, filteredColumns, sortedColumns, sortingVersus, maxResult, new ArrayList<String>());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public TableData fetch(int startIndex,
            List<TableCriteria> filteredColumns,
            List<String> sortedColumns,
            List<SortType> sortingVersus,
            int maxResult, List<String> lstJoin) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = builder.createQuery(voClass);
            Root<T> root = cq.from(voClass);
            Map<String, From> mapJoin = buildJoinMap(root, lstJoin, true);
            cq.select(root);
            cq.where(buildPredicates(filteredColumns, root, mapJoin));
//        long count = count(predicates, root);
            long count = count(filteredColumns, lstJoin);
            // ordering
            cq.orderBy(new ArrayList<Order>());
            for (int i = 0; i < sortedColumns.size(); i++) {
                From from = root;
                String attributeName = sortedColumns.get(i);
                if (attributeName.contains(".")) {
                    from = mapJoin.get(attributeName.substring(0, attributeName.lastIndexOf(".")));
                    attributeName = attributeName.substring(attributeName.lastIndexOf(".") + 1, attributeName.length());
                }
                if (sortingVersus.get(i) == SortType.DESCENDING) {
                    cq.getOrderList().add(builder.desc(from.get(attributeName)));
                } else {
                    cq.getOrderList().add(builder.asc(from.get(attributeName)));
                }
            }

            TypedQuery<T> typedQuery = em.createQuery(cq);

            typedQuery.setFirstResult(startIndex);
            typedQuery.setMaxResults(maxResult + 1);
            List<T> result = typedQuery.getResultList();
            boolean moreRows = result.size() > maxResult;
            if (moreRows) {
                result.remove(maxResult);// remove the last row
            }
            TableData tb = new TableData(new ArrayList<>(result), moreRows, count);
            //em.close();
            return tb;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Map<String, From> buildJoinMap(Root<T> root, List<String> lstJoin, boolean fetchJoin) {
        try {
            Map<String, From> mapJoin = new HashMap<>();
            for (String joinName : lstJoin) {
                From<?, ?> from;
                String joinTable = joinName;
                if (joinName.contains(".")) {
                    String parentTable = joinName.substring(0, joinName.lastIndexOf("."));
                    from = mapJoin.get(parentTable);
                    joinName = joinName.substring(joinName.lastIndexOf(".") + 1, joinName.length());
                } else {
                    from = root;
                }
                Join theJoin = from.join(joinName, JoinType.LEFT);
                if (fetchJoin) {
                    from.fetch(joinName, JoinType.LEFT);
                }
                mapJoin.put(joinTable, theJoin);
            }
            return mapJoin;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private long count(List<TableCriteria> filteredColumns,
            List<String> lstJoin) {
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = builder.createQuery(Long.class);
            Root<T> root = cq.from(voClass);
            Map<String, From> mapJoin = buildJoinMap(root, lstJoin, false);

            cq.select(builder.count(root));
            cq.where(buildPredicates(filteredColumns, root, mapJoin));

            long result = em.createQuery(cq).getSingleResult();

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private Predicate[] buildPredicates(List<TableCriteria> filteredColumns, Root<T> root, Map<String, From> mapJoin) {
        try {
            List<Predicate> lstPredicates = new ArrayList<>();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            for (TableCriteria tableCriteria : filteredColumns) {
                From from = root;
                String attributeName = tableCriteria.getAttributeName();
                if (tableCriteria.getAttributeName().contains(".")) {
                    from = mapJoin.get(attributeName.substring(0, attributeName.lastIndexOf(".")));
                    attributeName = attributeName.substring(attributeName.lastIndexOf(".") + 1, attributeName.length());
                }
                Comparable comparable;
                TableCriteria.Operator operator = tableCriteria.getOperator();
                Object value = tableCriteria.getValue();
                switch (operator) {
                    case eq:
                        lstPredicates.add(builder.equal(from.get(attributeName), value));
                        break;
                    case ne:
                        lstPredicates.add(builder.notEqual(from.get(attributeName), value));
                        break;
                    case le:
                        comparable = (Comparable) value;
                        lstPredicates.add(builder.lessThanOrEqualTo(from.<Comparable>get(attributeName), comparable));
                        break;
                    case lt:
                        comparable = (Comparable) value;
                        lstPredicates.add(builder.lessThan(from.<Comparable>get(attributeName), comparable));
                        break;
                    case ge:
                        comparable = (Comparable) value;
                        lstPredicates.add(builder.greaterThanOrEqualTo(from.<Comparable>get(attributeName), comparable));
                        break;
                    case gt:
                        comparable = (Comparable) value;
                        lstPredicates.add(builder.greaterThan(from.<Comparable>get(attributeName), comparable));
                        break;
                    case like_begin:
                        lstPredicates.add(builder.like(from.<String>get(attributeName), value.toString() + "%"));
                        break;
                    case like_anywhere:
                        lstPredicates.add(builder.like(from.<String>get(attributeName), "%" + value.toString() + "%"));
                        break;
                    case like_end:
                        lstPredicates.add(builder.like(from.<String>get(attributeName), "%" + value.toString()));
                        break;
                    case ilike_begin:
                        lstPredicates.add(builder.like(builder.lower(from.<String>get(attributeName)), value.toString().toLowerCase() + "%"));
                        break;
                    case ilike_anywhere:
                        lstPredicates.add(builder.like(builder.lower(from.<String>get(attributeName)), "%" + value.toString().toLowerCase() + "%"));
                        break;
                    case ilike_end:
                        lstPredicates.add(builder.like(builder.lower(from.<String>get(attributeName)), "%" + value.toString().toLowerCase()));
                        break;
                    case is_null:
                        lstPredicates.add(builder.isNull(from.get(attributeName)));
                        break;
                    case is_not_null:
                        lstPredicates.add(from.get(attributeName).isNotNull());
                        break;
                    case in:
                        lstPredicates.add(from.get(attributeName).in(value));
                        break;
                    case not_in:
                        lstPredicates.add(from.get(attributeName).in(value).not());
                        break;
                    default:

                }

            }
            Predicate[] predicates = new Predicate[]{};

            predicates = lstPredicates.toArray(predicates);
            return predicates;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method doesn't work in Hibernate. Hibernate can't reuse predicates
     * and root. Maybe because alias generation conflict.
     *
     * @param records
     * @return
     */
//    private long count(Predicate[] predicates, Root root) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
//        cq.select(builder.count(root));
//        if (predicates != null && predicates.length > 0) {
//            cq.where(predicates);
//        }
//        return em.createQuery(cq).getSingleResult();
//    }
    public List<T> insert(List<T> records) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            for (T record : records) {
                em.persist(record);
            }
            em.getTransaction().commit();
            //em.close();
        } catch (Exception ex) {
            if (em.isOpen()) {
                em.getTransaction().rollback();
                //em.close();
            }
            ex.printStackTrace();
            throw ex;
        }
        return records;
    }

    public T insert(T record) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(record);
            em.getTransaction().commit();
            //em.close();
        } catch (Exception ex) {
            if (em.isOpen()) {
                em.getTransaction().rollback();
                //em.close();
            }
            ex.printStackTrace();
            throw ex;
        }
        return record;
    }

    public List<T> delete(List<T> records) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            for (T record : records) {
                em.remove(em.merge(record));
            }
            em.getTransaction().commit();
            //em.close();
            return records;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public T delete(T record) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.remove(record);
            em.getTransaction().commit();
            //em.close();
            return record;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<T> update(List<T> records) {
        /**
         * This list will hold new object returned from merge execution. The new
         * object should have higher version than the old one
         */
        try {
            List<T> result = new ArrayList<>();
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            for (T record : records) {
                result.add(em.merge(record));
            }
            em.getTransaction().commit();
            //em.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public T update(T record) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            record = em.merge(record);
            em.getTransaction().commit();
            //em.close();
            return record;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<T> initRelationship(List<T> records, String joinTable) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = builder.createQuery(voClass);
            Root<T> root = cq.from(voClass);
            Join theJoin = root.join(joinTable, JoinType.LEFT);
            root.fetch(joinTable, JoinType.LEFT);
            cq.select(root);
            cq.where(root.in(records));
            TypedQuery<T> typedQuery = em.createQuery(cq);
            List<T> result = typedQuery.getResultList();
            //em.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public T initRelationship(T record, String joinTable) {
        try {
            //em = utils.getEntityManagerFactory().createEntityManager();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = builder.createQuery(voClass);
            Root<T> root = cq.from(voClass);
            Join theJoin = root.join(joinTable, JoinType.LEFT);
            root.fetch(joinTable, JoinType.LEFT);
            cq.select(root);
            cq.where(builder.equal(root, record));
            TypedQuery<T> typedQuery = em.createQuery(cq);
            T result = typedQuery.getSingleResult();
            //em.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
