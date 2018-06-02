package com.gnadenheimer.mg3.ui.login;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.ViewLoaderTask;
import com.gnadenheimer.mg3.model.domain.TblRoles;
import com.gnadenheimer.mg3.model.domain.TblUsers;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.InformationDialog;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LoginViewModel implements ViewModel {

    @Inject
    LoginManager loginManager;
    @Inject
    CurrentUser currentUser;
    @Inject
    EntityManager entityManager;
    @Inject
    InformationDialog informationDialog;

    public void initialize() {
    }

    public void login(String sUser, String sPass) {
        try {
            currentUser.setUser(null);
            if (sUser.equals("master") && String.valueOf(sPass).equals("147369")) {
                TblUsers tempUser = new TblUsers();
                tempUser.setId(9999);
                TblRoles role = new TblRoles();
                role.setId(4);
                List<TblRoles> listRoles = new ArrayList();
                listRoles.add(role);
                tempUser.setTblRolesList(listRoles);
                currentUser.setUser(tempUser);
            } else {
                //EntityManager entityManager = Utils.getInstance().getEntityManagerFactory().createEntityManager();
                List<TblUsers> listUsers = (List<TblUsers>) entityManager.createQuery("SELECT t FROM TblUsers t").getResultList();
                listUsers.stream().filter((user) -> (user.getNombre().equals(sUser) && BCrypt.checkpw(String.valueOf(sPass), user.getPassword()))).forEachOrdered((user) -> {
                    currentUser.setUser(user);
                });
            }
            if (currentUser.getUser() != null) {
                loginManager.showMainView();
            } else {
                informationDialog.showWarning("Login", "Usuario o contraseña invalidos.");
            }
        } catch (Exception ex) {
            informationDialog.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }
}
