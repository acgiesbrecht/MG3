package com.gnadenheimer.mg3.ui.menu;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.ViewLoaderTask;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.Properties;

public class MenuViewModel implements ViewModel {

    @Inject
    LoginManager loginManager;
    @Inject
    Utils utils;

    public void initialize() {
    }

    public void logout(){
        loginManager.logout();
    }

    public void loadView(Class tClass, String name) {
        ViewLoaderTask<Parent> loadTask = new ViewLoaderTask<Parent>(tClass) {
            @Override
            protected Parent call() {
                try {
                    return FluentViewLoader.fxmlView(this.aClass).load().getView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        };
        loadTask.setOnSucceeded(evento -> {
            try {
                App.getStage().setTitle("MG "+utils.getVersion() + " - " + name);
                App.getRootBorderPane().setCenter(loadTask.getValue());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        new Thread(loadTask).start();
    }


}
