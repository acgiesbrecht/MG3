/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.ui;

import com.gnadenheimer.mg3.App;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class LoginView implements FxmlView<LoginViewModel> {

    @InjectViewModel
    private LoginViewModel viewModel;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button cmdLogin;

    @FXML
    private void login(ActionEvent event) {
        viewModel.login(txtUser.getText(), txtPass.getText());
    }

    public void initialize() {
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
