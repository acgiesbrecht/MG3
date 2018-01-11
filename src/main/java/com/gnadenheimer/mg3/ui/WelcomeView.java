package com.gnadenheimer.mg3.ui;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

public class WelcomeView implements FxmlView<WelcomeViewModel> {

    @InjectViewModel
    private WelcomeViewModel viewModel;

}
