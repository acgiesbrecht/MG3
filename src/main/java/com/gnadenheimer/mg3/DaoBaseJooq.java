/*
 * License BSD License
 * Copyright (C) 2013 Amrullah <amrullah@panemu.com>.
 */
package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.Utils;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TableColumn.SortType;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class DaoBaseJooq {

    public DaoBaseJooq() {
    }

    public Connection getConnection() {
        try {
            Map<String, String> persistenceMap = Utils.getInstance().getPersistenceMap();
            String url = persistenceMap.get("javax.persistence.jdbc.url");
            String userName = persistenceMap.get("javax.persistence.jdbc.user");
            String password = persistenceMap.get("javax.persistence.jdbc.password");
            Connection conn = DriverManager.getConnection(url, userName, password);
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public DSLContext getDsl() {
        DSLContext dsl = DSL.using(getConnection(), SQLDialect.DERBY);
        return dsl;
    }

    /* public TableData fetch(int startIndex,
            List<TableCriteria> filteredColumns,
            List<String> sortedColumns,
            List<SortType> sortingVersus,
            int maxResult, List<String> lstJoin) {
        try {
            Result<Record> result = getDsl().select().from(this.voClass).fetch();

            //TableData tb = new TableData(new ArrayList<>(result), moreRows, count);
            //em.close();
            //return tb;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<T> insert(List<T> records) {
        try {
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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

        try {
            List<T> result = new ArrayList<>();
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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
            //em = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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
     */
}
