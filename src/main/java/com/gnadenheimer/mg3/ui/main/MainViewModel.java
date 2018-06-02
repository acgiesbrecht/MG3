package com.gnadenheimer.mg3.ui.main;

import com.gnadenheimer.mg3.utils.Utils;
import de.saxsys.mvvmfx.ViewModel;

import javax.inject.Inject;


public class MainViewModel implements ViewModel {

    @Inject
    Utils utils;

    public void initialize() {
    }

    public String getVersion() {
        return "3" + utils.getVersion();
    }
}
