package com.gnadenheimer.mg3.model.dao;

import com.gnadenheimer.mg3.utils.InformationDialog;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    Logger LOGGER;
    @Inject
    InformationDialog informationDialog;

    @Produces
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("mg_PU", getPersistenceMap())
                .createEntityManager();
    }

    public void close(
            @Disposes EntityManager entityManager) {
        entityManager.close();
    }

    public Map<String, String> getPersistenceMap() {
        try {
            String databaseIP;
            databaseIP = Preferences.userRoot().node("MG").get("DatabaseIP", "127.0.0.1");
            Map<String, String> persistenceMap = new HashMap<>();
            persistenceMap.put("javax.persistence.jdbc.url", "jdbc:derby://" + databaseIP + ":1527/mgdb;create=true");
            persistenceMap.put("javax.persistence.jdbc.user", "mg");
            persistenceMap.put("javax.persistence.jdbc.password", "123456");
            persistenceMap.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.ClientDriver");
            //persistenceMap.put("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
            //persistenceMap.put("hibernate.show_sql", "false");
            //persistenceMap.put("hibernate.connection.release_mode", "auto");
            //persistenceMap.put("current_session_context_class", "thread");
            /*persistenceMap.put("hibernate.connection.autoReconnect", "true");
            persistenceMap.put("hibernate.c3p0.min_size", "5");
            persistenceMap.put("hibernate.c3p0.max_size", "20");
            persistenceMap.put("hibernate.c3p0.timeout", "500");
            persistenceMap.put("hibernate.c3p0.max_statements", "50");
            persistenceMap.put("hibernate.c3p0.idle_test_period", "2000");
            persistenceMap.put("hibernate.c3p0.testConnectionOnCheckout", "true");
            persistenceMap.put("connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");*/

            persistenceMap.put("backUpDir", Preferences.userRoot().node("MG").get("Datadir", System.getProperty("user.dir") + File.separator + "javadb") + File.separator + "autoBackUp");
            return persistenceMap;
        } catch (Exception exx) {
            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), exx.getMessage(), exx);
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
            return null;
        }
    }

}
