package com.gnadenheimer.mg3.ui.main.menu;

import com.gnadenheimer.mg3.utils.LoginManager;
import de.saxsys.mvvmfx.ViewModel;

import javax.inject.Inject;

public class MenuViewModel implements ViewModel {

    @Inject
    LoginManager loginManager;

    public void logout() {
        loginManager.logout();
    }

}
