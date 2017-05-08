package org.sebo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sebo.controller.Controller;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL view = getClass().getClassLoader().getResource("view.fxml");
//        System.out.println(view);
        FXMLLoader loader = new FXMLLoader(view);

        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Subliner");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
