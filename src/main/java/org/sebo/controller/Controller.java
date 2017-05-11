package org.sebo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.StatusBar;
import org.sebo.SubtitleUtil;
import org.sebo.model.Subtitle;
import org.sebo.model.TextBlock;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private final ObservableList<TextBlock> data = FXCollections.observableArrayList();
    @FXML
    public TextArea originalText;
    @FXML
    public TextArea translateText;
    @FXML
    public TableView<TextBlock> table;
    public MenuItem importButton;
    public StatusBar statusBar;
    private Stage stage;

    public void onImport(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Subtitle File");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Subtitle",
                "*.srt", "*.stl");

        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }
        try {
            data.clear();
            Subtitle subtitle = SubtitleUtil.loadSubtitle(file);
            data.addAll(subtitle.getAll());

        } catch (Exception e) {
            e.printStackTrace();
            statusBar.setText(e.getMessage());
        }
    }

    public void setStage(Stage stageAndSetupListeners) {
        this.stage = stageAndSetupListeners;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTable();

        translateText.textProperty()
                     .addListener((observable, oldValue, newValue) -> {
                         table.getSelectionModel()
                              .getSelectedItem()
                              .setTranslation(newValue);
                         table.refresh();
                     });
    }

    private void initTable() {
        TableColumn<TextBlock, String> start = new TableColumn<>("Start");
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        table.getColumns().add(start);

        TableColumn<TextBlock, String> end = new TableColumn<>("End");
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        table.getColumns().add(end);

        TableColumn<TextBlock, String> sub = new TableColumn<>("Subtitle");
        sub.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        table.getColumns().add(sub);

        TableColumn<TextBlock, String> translation = new TableColumn<>("Translation");
        translation.setCellValueFactory(new PropertyValueFactory<>("translation"));
        table.getColumns().add(translation);

        table.setItems(data);
        table.getSelectionModel()
             .selectedItemProperty()
             .addListener((observable, oldValue, newValue) -> {
                 if (newValue != null) {
                     originalText.setText(newValue.getSubtitle());
                     translateText.setText(newValue.getTranslation());
                 }
             });
    }

    public void onCopyDown(ActionEvent event) {
        translateText.setText(originalText.getText());
    }
}
