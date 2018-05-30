package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.InformationDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.controlsfx.control.textfield.CustomTextField;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;

public class AbstractView<T> {

    private final static int rowsPerPage = 500;
    public VBox mainBox = new VBox();
    public Label lblFilterSize = new Label();
    public CustomTextField txtFilter = new CustomTextField();
    public SplitPane splitPane = new SplitPane();
    public AnchorPane masterAnchorPane = new AnchorPane();
    public VBox tableBox = new VBox();
    public VBox vBox = new VBox();
    public HBox hbox = new HBox();
    public Pagination pagination = new Pagination();
    public TableView<T> itemsTable = new TableView<>();
    public GridPane gridPane = new GridPane();
    public Button btnAdd = new Button("Nuevo");
    public Button btnSave = new Button("Guardar");
    public Button btnDelete = new Button("Eliminar");
    public Button btnReset = new Button("Cancelar");
    public ProgressIndicator progressIndicator = new ProgressIndicator(-1.0);
    public ButtonBar buttonBar = new ButtonBar();
    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    public StackPane mainStackPane;
    public BooleanProperty isNewProperty = new SimpleBooleanProperty();
    public IntegerProperty pageCountProperty = new SimpleIntegerProperty();
    public FilteredList<T> filteredData;
    public SortedList<T> sortedData;
    @Inject
    InformationDialog dialog;
    private AbstractViewModel<T> viewModel;
    private BooleanBinding isSelectedOrNewBinding;
    private BooleanBinding isDifferentOrNewBinding;

    public void initializeAbstract() {

        splitPane.setDividerPositions(0.7);
        splitPane.setOrientation(Orientation.VERTICAL);
        VBox.setVgrow(splitPane, Priority.ALWAYS);

        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        hbox.setSpacing(5.0);

        lblFilterSize.setStyle("-fx-text-fill:gray");
        hbox.getChildren().add(lblFilterSize);

        txtFilter.getStyleClass().add("searchField");
        Label label = new Label();
        label.getStyleClass().add("searchBoxLabel");
        txtFilter.setLeft(label);
        FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView();
        fontAwesomeIconView.getStyleClass().add("searchBoxLabelIcon");
        label.setGraphic(fontAwesomeIconView);
        hbox.getChildren().add(txtFilter);

        itemsTable.setPlaceholder(new Label("Sin datos."));
        tableBox.getChildren().setAll(itemsTable);
        vBox.getChildren().setAll(hbox, tableBox, pagination);
        vBox.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        AnchorPane.setBottomAnchor(vBox, 10.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setTopAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
        masterAnchorPane.getChildren().add(vBox);

        AnchorPane editPane = new AnchorPane();

        gridPane.setHgap(10.0);
        gridPane.setVgap(10.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setTopAnchor(gridPane, 0.0);
        editPane.getChildren().add(gridPane);

        splitPane.getItems().addAll(masterAnchorPane, editPane);

        AnchorPane buttonBarPane = new AnchorPane();
        AnchorPane.setBottomAnchor(buttonBar, 10.0);
        AnchorPane.setTopAnchor(buttonBar, 10.0);
        AnchorPane.setLeftAnchor(buttonBar, 10.0);
        AnchorPane.setRightAnchor(buttonBar, 10.0);

        buttonBar.getButtons().addAll(btnAdd, btnDelete, btnReset, btnSave);
        buttonBarPane.getChildren().add(buttonBar);

        mainBox.getChildren().addAll(splitPane, buttonBarPane);

        VBox loadingIndicator = new VBox();
        loadingIndicator.setAlignment(Pos.CENTER);
        loadingIndicator.setFillWidth(false);
        loadingIndicator.getChildren().add(progressIndicator);

        mainStackPane.getChildren().addAll(mainBox, loadingIndicator);

        btnSave.setOnAction((event) -> {
            getViewModel().save();
            isNewProperty.set(false);
            itemsTable.refresh();
        });

        btnDelete.setOnAction((event) -> {
            getViewModel().delete();
            itemsTable.refresh();
        });

        btnReset.setOnAction((event) -> {
            isNewProperty.set(false);
            getViewModel().reset();
        });

        loadingIndicator.visibleProperty().bind(viewModel.loadingInProgressProperty());
        mainBox.disableProperty().bind(viewModel.loadingInProgressProperty());
        itemsTable.visibleProperty().bind(viewModel.loadingInProgressProperty().not());

        filteredData = new FilteredList<>(viewModel.getItems(), p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(itemsTable.comparatorProperty());
        itemsTable.setItems(sortedData);

        filteredData.addListener((ListChangeListener) (c -> {
            if (!txtFilter.getText().isEmpty()) {
                lblFilterSize.setText("Se filtraron " + String.valueOf(filteredData.size()) + " registros.");
            }
        }));

        sortedData.addListener((ListChangeListener) (c -> {
            int numOfPages = 1;
            if (sortedData.size() % rowsPerPage == 0) {
                numOfPages = sortedData.size() / rowsPerPage;
            } else if (sortedData.size() > rowsPerPage) {
                numOfPages = sortedData.size() / rowsPerPage + 1;
            }
            if (numOfPages == 0) {
                numOfPages = 1;
            }
            pagination.setPageFactory(this::createPage);
            pagination.setPageCount(numOfPages);
        }));

        viewModel.selectedItemProperty().bind(itemsTable.getSelectionModel().selectedItemProperty());

        // When the selectedTableRowProperty changes in the viewModel we need to update the master
        viewModel.setOnSelect(vm -> itemsTable.getSelectionModel().select(vm));

        BooleanBinding isSelectedBinding = Bindings.size(itemsTable.getSelectionModel().getSelectedItems()).greaterThan(0);
        isSelectedOrNewBinding = isSelectedBinding.or(isNewProperty);
        isDifferentOrNewBinding = getViewModel().differentProperty().or(isNewProperty);

        itemsTable.disableProperty().bind(isDifferentOrNewBinding);
        btnSave.disableProperty().bind(isDifferentOrNewBinding.not());
        btnDelete.disableProperty().bind(isSelectedBinding.not().or(isNewProperty));

        editPane.disableProperty().bind(isSelectedOrNewBinding.not());
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, sortedData.size());
        itemsTable.setItems(FXCollections.observableArrayList(sortedData.subList(fromIndex, toIndex)));

        return new VBox(itemsTable);
    }

    public void addAbstract() {
        isNewProperty.set(true);
        itemsTable.getSelectionModel().clearSelection();
    }

    public AbstractViewModel<T> getViewModel() {
        return viewModel;
    }

    public void setViewModel(AbstractViewModel<T> viewModel) {
        this.viewModel = viewModel;
    }

}
