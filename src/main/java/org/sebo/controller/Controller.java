package org.sebo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public MenuItem importButton;
    public TableView<TextBlock> table;
    public TextArea originalText;
    public TextArea translateText;
    public StatusBar statusBar;

    private Stage stage;
    private ObservableList<TextBlock> data = FXCollections.observableArrayList();

    public void onImport() {
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTable();

        translateText.textProperty()
                     .addListener((observable, oldValue, newValue) -> {
                         if (table.getItems().size() > 0) {
                             table.getSelectionModel()
                                  .getSelectedItem()
                                  .setTranslation(newValue);
                             table.refresh();
                         }
                     });
    }

    private void initTable() {
        TableColumn<TextBlock, String> start = new TableColumn<>("Start");
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        start.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
        start.setResizable(false);

        TableColumn<TextBlock, String> end = new TableColumn<>("End");
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        end.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
        end.setResizable(false);
        end.setSortable(false);

        TableColumn<TextBlock, String> sub = new TableColumn<>("Subtitle");
        sub.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        sub.prefWidthProperty().bind(table.widthProperty().multiply(0.375));
        sub.setResizable(false);
        sub.setSortable(false);

        TableColumn<TextBlock, String> tran = new TableColumn<>("Translation");
        tran.setCellValueFactory(new PropertyValueFactory<>("translation"));
        tran.prefWidthProperty().bind(table.widthProperty().multiply(0.375));
        tran.setResizable(false);
        tran.setSortable(false);

        table.getColumns().addAll(start, end, sub, tran);

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

    public void onCopyDown() {
        translateText.setText(originalText.getText());
    }
}
