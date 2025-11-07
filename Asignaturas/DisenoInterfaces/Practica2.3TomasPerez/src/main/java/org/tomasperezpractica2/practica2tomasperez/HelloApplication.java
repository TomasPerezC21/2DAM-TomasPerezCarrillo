package org.tomasperezpractica2.practica2tomasperez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainLayouts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tomás Pérez Carrillo");
        stage.setScene(scene);
        stage.show();
    }
}
