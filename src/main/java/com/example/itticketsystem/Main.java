package com.example.itticketsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/itticketsystem/style.css").toExternalForm());

        stage.setTitle("IT TICKET SYSTEM");
        stage.setScene(scene);
        stage.toFront();            // Automatically pops up
        stage.setResizable(false);  // Stops resizing
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
