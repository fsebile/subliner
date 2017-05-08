package org.sebo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public class Controller {

    @FXML
    public TextArea originalText;

    @FXML
    public TextArea translateText;

    @FXML
    public TableView table;

    public void handleClose(ActionEvent event) {
//        System.out.println(event);
        System.exit(0);
    }
}
