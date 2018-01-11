package com.gnadenheimer.mg3.dao;

import com.gnadenheimer.mg3.utils.Utils;
import org.apache.derby.drda.NetworkServerControl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    Utils utils;

    public EntityManagerProducer() {
        try {
            if (Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true"))) {
                Properties p = System.getProperties();
                p.setProperty("derby.system.home", Preferences.userRoot().node("MG").get("Datadir", System.getProperty("user.dir") + File.separator + "javadb"));
                p.setProperty("derby.drda.host", "0.0.0.0");
                p.setProperty("derby.language.sequence.preallocator", "1");
                NetworkServerControl server = new NetworkServerControl();
                server.start(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Produces
    public EntityManager createEntityManager() {

        Map<String, String> persistenceMap = utils.getPersistenceMap();
        return Persistence
                .createEntityManagerFactory("mg_PU", persistenceMap)
                .createEntityManager();
    }


    public void close(
            @Disposes EntityManager entityManager) {
        entityManager.close();
    }


}
