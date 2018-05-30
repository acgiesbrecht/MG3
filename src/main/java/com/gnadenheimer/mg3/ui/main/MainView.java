package com.gnadenheimer.mg3.ui.main;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MainView implements FxmlView<MainViewModel> {

    @InjectViewModel
    private MainViewModel viewModel;

    @FXML
    private ImageView welcomeImage;

    @FXML
    private Text txtVersion;

    public void initialize() {
        //welcomeImage.setImage(new Image(this.getClass().getResourceAsStream("/images/membrete-cin-header.png")));
    }
}
