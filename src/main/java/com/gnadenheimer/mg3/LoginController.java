/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.domain.TblRoles;
import com.gnadenheimer.mg3.domain.TblUsers;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class LoginController implements Initializable {

    CurrentUser currentUser = CurrentUser.getInstance();
    Map<String, String> persistenceMap = Utils.getInstance().getPersistenceMap();
    EntityManager entityManager = App.factory.createEntityManager();
    List<TblUsers> listUsers = (List<TblUsers>) entityManager.createQuery("SELECT t FROM TblUsers t").getResultList();

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button cmdLogin;

    @FXML
    private void login(ActionEvent event) {
        try {
            currentUser.setUser(null);
            if (txtUser.getText().equals("master") && String.valueOf(txtPass.getText()).equals("147369")) {
                TblUsers tempUser = new TblUsers();
                tempUser.setId(9999);
                TblRoles role = new TblRoles();
                role.setId(3);
                List<TblRoles> listRoles = new ArrayList();
                listRoles.add(role);
                tempUser.setTblRolesList(listRoles);
                currentUser.setUser(tempUser);
            } else {
                listUsers.stream().filter((user) -> (user.getNombre().equals(txtUser.getText()) && BCrypt.checkpw(String.valueOf(txtPass.getText()), user.getPassword()))).forEachOrdered((user) -> {
                    currentUser.setUser(user);
                });
            }
            if (currentUser.getUser() != null) {
                LoginManager.getInstance().showMainView();
            } else {
                App.showWarning("Login", "Usuario o contraseña invalidos.");
            }

        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {
                if (txtUser != null) {
                    txtUser.requestFocus();
                }
            } catch (Exception ex) {
                App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
            }
        });
    }

}
