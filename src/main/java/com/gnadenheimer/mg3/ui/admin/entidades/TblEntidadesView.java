package com.gnadenheimer.mg3.ui.admin.entidades;

import com.gnadenheimer.mg3.AbstractView;
import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import com.gnadenheimer.mg3.ui.fieldextensions.TableColumnInteger;
import com.gnadenheimer.mg3.ui.fieldextensions.TableColumnString;
import com.gnadenheimer.mg3.ui.fieldextensions.TextFieldBase;
import com.gnadenheimer.mg3.ui.fieldextensions.TextFieldInteger;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class TblEntidadesView extends AbstractView<TblEntidades> implements FxmlView<TblEntidadesViewModel> {

    private TextFieldInteger txtId = new TextFieldInteger();
    private TextFieldBase txtApellidos = new TextFieldBase();
    private TextFieldBase txtNombres = new TextFieldBase();

    @InjectViewModel
    private TblEntidadesViewModel viewModel;

    public void initialize() {
        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<TblEntidades> col1 = new TableColumnInteger<>("ID", "id", 50.0);
        TableColumnString<TblEntidades> col2 = new TableColumnString<>("Apellidos", "apellidos", 250.0);
        TableColumnString<TblEntidades> col3 = new TableColumnString<>("Nombres", "nombres", 250.0);

        itemsTable.getColumns().addAll(col1, col2, col3);

        gridPane.add(new Label("ID"), 1, 1);
        gridPane.add(new Label("Apellidos"), 1, 2);
        gridPane.add(new Label("Nombres"), 1, 3);

        gridPane.add(txtId, 2, 1);
        gridPane.add(txtApellidos, 2, 2);
        gridPane.add(txtNombres, 2, 3);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(entidad -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (entidad.getId().toString().contains(lowerCaseFilter)) {
                    return true;
                } else if (entidad.getNombres().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return entidad.getApellidos().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            txtId.textProperty().bindBidirectional(viewModel.idProperty(), new NumberStringConverter());
            txtNombres.textProperty().bindBidirectional(viewModel.nombresProperty());
            txtApellidos.textProperty().bindBidirectional(viewModel.apellidosProperty());
        });

        txtId.textProperty().bindBidirectional(viewModel.idProperty(), new NumberStringConverter());
        txtNombres.textProperty().bindBidirectional(viewModel.nombresProperty());
        txtApellidos.textProperty().bindBidirectional(viewModel.apellidosProperty());

        btnAdd.setOnAction((event) -> {
            addAbstract();
            viewModel.add(new TblEntidades());
            txtId.requestFocus();
        });
    }

}
