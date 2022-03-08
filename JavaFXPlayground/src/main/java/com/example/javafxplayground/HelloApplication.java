package com.example.javafxplayground;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage mainMenuStage) throws IOException {
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));

        root.getChildren().add(new Button("Add Student"));
        root.getChildren().add(new Button("Print by name (Alphabetized)"));
        root.getChildren().add(new Button("Print by class (Alphabetized)"));

        Image image = new Image(new FileInputStream("resize.gif"));
        root.getChildren().add(image);

        mainMenuStage.setTitle("Main Menu");
        Scene scene = new Scene(root, 400, 300);
        mainMenuStage.setScene(scene);

        mainMenuStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}