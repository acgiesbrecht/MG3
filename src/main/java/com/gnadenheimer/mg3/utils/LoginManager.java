/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils;


import com.gnadenheimer.mg3.model.domain.TblDatabaseUpdates;
import com.gnadenheimer.mg3.model.domain.TblRoles;
import com.gnadenheimer.mg3.model.domain.TblUsers;
import com.gnadenheimer.mg3.ui.login.LoginView;
import com.gnadenheimer.mg3.ui.main.MainView;
import com.gnadenheimer.mg3.ui.menu.MenuView;
import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class LoginManager {

    @Inject
    EntityManager entityManager;
    @Inject
    CurrentUser currentUser;
    @Inject
    private Stage primaryStage;
    @Inject
    Utils utils;
    @Inject
    InformationDialog informationDialog;

    //private static final LoginManager loginManager = new LoginManager();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private LoginManager() {
        try {
            //AUTO LOGIN-------------------------------
            currentUser.setUser(null);
            //EntityManager entityManager = utils.getEntityManagerFactory().createEntityManager();
            List<TblUsers> list = entityManager.createQuery("SELECT t FROM TblUsers t").getResultList();
            for (TblUsers user : list) {
                if (user.getNombre().equals("adrian") && BCrypt.checkpw(String.valueOf("adrian"), user.getPassword())) {
                    TblRoles role = new TblRoles();
                    role.setId(4);
                    List<TblRoles> listRoles = new ArrayList();
                    listRoles.add(role);
                    user.setTblRolesList(listRoles);
                    currentUser.setUser(user);
                }
            }

            //DATABASE UPDATE
            databaseUpdate();
        } catch (Exception ex) {
//            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    /* Static 'instance' method *
    public static LoginManager getInstance() {
        return loginManager;
    }*/

    public void logout() {
        showLoginView();
    }

    public void showLoginView() {
        try {
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();

            bp.setTop(null);
            bp.setCenter(FluentViewLoader.fxmlView(LoginView.class).load().getView());

        } catch (Exception ex) {
            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    public void showMainView() {
        try {
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();

            bp.setTop(FluentViewLoader.fxmlView(MenuView.class).load().getView());
            bp.setCenter(FluentViewLoader.fxmlView(MainView.class).load().getView());

        } catch (Exception ex) {
            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    public void setView(Parent view, String displayTitle) {
        try {
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();
            primaryStage.setTitle(utils.getMGTitle() + " " + displayTitle);
            if (currentUser.getUser() == null) {
                bp.setTop(null);
                bp.setCenter(FluentViewLoader.fxmlView(LoginView.class).load().getView());
            } else {
                bp.setTop(FluentViewLoader.fxmlView(MenuView.class).load().getView());
                bp.setCenter(view);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void databaseUpdate() {
        try {
            //EntityManager entityManager = utils.getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_users where 1=2").getSingleResult();
            } catch (Exception e) {
                utils.executeSQL("/sql/javadb.sql");
            }

            Boolean hasUpdated = false;

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_database_updates where 1=2").getSingleResult();
            } catch (Exception e) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160224.sql", hasUpdated);
                if (!hasUpdated) {
                    informationDialog.showException("ERROR de Base de Datos", e.getMessage(), e);
                }
            }

            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160219.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160219.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160323.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160323.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_contabilidad_y_compras.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_contabilidad_y_compras.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160330.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160330.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160409.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160409.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160429.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160429.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160507.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160507.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160601.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160601.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160715.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160715.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160810.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160810.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160815.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160815.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160902.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160902.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160905.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160905.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160912.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160912.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161104.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20161104.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161115.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20161115.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20170122.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20170122.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20170123.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20170123.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (hasUpdated) {
                informationDialog.showInfo("Exito", "La Base de datos se ha actualizado con exito.");
            }
        } catch (Exception ex) {
            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
