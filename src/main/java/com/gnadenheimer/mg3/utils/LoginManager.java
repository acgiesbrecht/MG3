/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.ui.LoginView;
import com.gnadenheimer.mg3.ui.LoginViewModel;
import com.gnadenheimer.mg3.ui.WelcomeView;
import com.gnadenheimer.mg3.ui.WelcomeViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LoginManager {

    @Inject
    private Stage primaryStage;


    private static final LoginManager loginManager = new LoginManager();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private LoginManager() {
    }

    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            final ViewTuple<LoginView, LoginViewModel> tuple = FluentViewLoader.fxmlView(LoginView.class).load();
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();
            bp.setTop(null);
            bp.setCenter(tuple.getView());
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    public void showMainView() {
        try {
            final ViewTuple<LoginView, LoginViewModel> menu = FluentViewLoader.fxmlView(LoginView.class).load();
            final ViewTuple<WelcomeView, WelcomeViewModel> welcome = FluentViewLoader.fxmlView(WelcomeView.class).load();
            BorderPane bp = (BorderPane) primaryStage.getScene().getRoot();
            bp.setTop(menu.getView());
            bp.setCenter(welcome.getView());

        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
