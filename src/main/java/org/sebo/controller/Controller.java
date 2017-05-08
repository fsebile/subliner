package org.sebo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.sebo.model.Subtitle;

import java.io.File;
import java.io.IOException;


public class Controller {

    @FXML
    public TextArea originalText;

    @FXML
    public TextArea translateText;

    @FXML
    public TableView table;

    public MenuItem importButton;
    private Stage stage;

    public void handleClose(ActionEvent event) {
//        System.out.println(event);
        System.exit(0);
    }

    public void onImport(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Subtitle File");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("SRT", ".srt"));
        File file = fileChooser.showOpenDialog(stage);
        try {
            loadSubtitle(file);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO error message
        }
    }

    private void loadSubtitle(File file) throws IOException {
        Subtitle subtitle = Subtitle.readSRT(file.getAbsolutePath());
//        System.out.println(subtitle.get(1));

        originalText.setText(subtitle.get(1).getSubtitle());
    }

    public void setStage(Stage stageAndSetupListeners) {
        this.stage = stageAndSetupListeners;
    }
}
