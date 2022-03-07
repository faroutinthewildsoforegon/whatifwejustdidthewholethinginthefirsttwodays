package com.example.tictactoe_v3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// This is a dumb placeholder driver.
public class Test extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Local 2 Player Game");
        stage.show();

        /*  This'll be the version with the computer player
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Player vs. AI");
        stage.show();
        */

    }

    public static void main(String[] args) {
        launch();
    }
}