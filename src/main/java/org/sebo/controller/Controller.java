package org.sebo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.sebo.model.Subtitle;
import subtitleFile.FatalParsingException;

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

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Subtitle",
                "*.srt", "*.stl");

        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(stage);
        try {
            loadSubtitle(file);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO error message
        } catch (FatalParsingException e) {
            e.printStackTrace();
        }
    }

    private void loadSubtitle(File file) throws IOException, FatalParsingException {

        //TODO move to Helper class
        if (file.getName().endsWith(".srt")) {
            Subtitle subtitle = Subtitle.readSRT(file.getAbsolutePath());
            originalText.setText(subtitle.get(1).getSubtitle());
        } else if (file.getName().endsWith(".stl")) {
            Subtitle subtitle1 = Subtitle.readSTL(file.getAbsolutePath());
            originalText.setText(subtitle1.get(1).getSubtitle());
        } else {
            System.out.println("Uygun formatta dosya olmalı");
        }

    }

    public void setStage(Stage stageAndSetupListeners) {
        this.stage = stageAndSetupListeners;
    }
}
