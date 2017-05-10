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
import org.sebo.model.Subtitle;
import org.sebo.model.TableModel;
import org.sebo.model.TextBlock;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private final ObservableList<TableModel> data = FXCollections.observableArrayList();
    @FXML
    public TextArea originalText;
    @FXML
    public TextArea translateText;
    @FXML
    public TableView<TableModel> table;
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
        try {
            Subtitle subtitle = loadSubtitle(file);

            subtitle.getAll()
                    .stream()
                    .map(TextBlock::getTableModel)
                    .forEach(data::add);

        } catch (Exception e) {
            e.printStackTrace();
            statusBar.setText(e.getMessage());
        }
    }

    private Subtitle loadSubtitle(File file) throws Exception {

        Subtitle subtitle;
        //TODO move to Helper class
        if (file.getName().endsWith(".srt")) {
            subtitle = Subtitle.readSRT(file.getAbsolutePath());
//            originalText.setText(subtitle.get(1).getSubtitle());
        } else if (file.getName().endsWith(".stl")) {
            subtitle = Subtitle.readSTL(file.getAbsolutePath());
//            originalText.setText(subtitle1.get(1).getSubtitle());
        } else {
            System.out.println("Uygun formatta dosya olmalı");
            throw new Exception("Uygun formatta dosya olmalı");
        }
        return subtitle;
    }

    public void setStage(Stage stageAndSetupListeners) {
        this.stage = stageAndSetupListeners;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        TableColumn<TableModel, Integer> id = new TableColumn<>("#");
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        table.getColumns().add(id);

        TableColumn<TableModel, String> start = new TableColumn<>("Start");
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        table.getColumns().add(start);

        TableColumn<TableModel, String> end = new TableColumn<>("End");
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        table.getColumns().add(end);

        TableColumn<TableModel, String> sub = new TableColumn<>("Subtitle");
        sub.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        table.getColumns().add(sub);

        TableColumn<TableModel, String> translation = new TableColumn<>("Translation");
        translation.setCellValueFactory(new PropertyValueFactory<>("translation"));
        table.getColumns().add(translation);

        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                originalText.setText(newValue.getSubtitle());
                translateText.setText(newValue.getTranslation());
            }
        });

        translateText.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    table.getSelectionModel()
                            .getSelectedItem()
                            .setTranslation(newValue);
                    table.refresh();
                });
    }

    public void onCopyDown(ActionEvent event) {
        translateText.setText(originalText.getText());
    }
}
