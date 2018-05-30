/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils;

import com.gnadenheimer.mg3.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class LoginManager {

    private static final LoginManager loginManager = new LoginManager();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private LoginManager() {
    }

    /* Static 'instance' method */
    @Produces
    public static LoginManager getInstance() {
        return loginManager;
    }

    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            //scene.setRoot((Parent) loader.load());
            App.getRoot().setTop(null);
            App.getRoot().setCenter(login);

        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    public void showMainView() {
        try {

            MenuBar menu = FXMLLoader.load(getClass().getResource("/fxml/MenuPrincipal.fxml"));
            AnchorPane welcomePane = FXMLLoader.load(getClass().getResource("/fxml/Welcome.fxml"));

            App.getRoot().setTop(menu);
            App.getRoot().setCenter(welcomePane);

        } catch (IOException ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
