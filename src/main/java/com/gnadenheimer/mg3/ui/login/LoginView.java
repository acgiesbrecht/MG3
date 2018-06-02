package com.gnadenheimer.mg3.ui.login;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.ui.admin.entidades.TblEntidadesView;
import com.gnadenheimer.mg3.utils.CurrentUser;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class LoginView implements FxmlView<LoginViewModel> {

    @Inject
    CurrentUser currentUser;
    @InjectViewModel
    private LoginViewModel viewModel;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button cmdLogin;
    @FXML
    private Button cmdCancel;

    @FXML
    private void login() {
        viewModel.login(txtUser.getText(), txtPass.getText());
    }

    public void initialize() {
        Platform.runLater(() -> {
            try {
                if (txtUser != null) {
                    txtUser.requestFocus();
                }
            } catch (Exception ex) {
                //App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
                ex.printStackTrace();
            }
        });
    }


}
