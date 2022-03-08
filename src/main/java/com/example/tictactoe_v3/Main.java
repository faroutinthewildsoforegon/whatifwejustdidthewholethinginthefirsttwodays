package com.example.tictactoe_v3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static int MAINMENU_WINDOW_WIDTH = 670;
    public static int MAINMENU_WINDOW_HEIGHT= 490;

    public static Stage GLOBAL_MENU_STAGE;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(root, Main.MAINMENU_WINDOW_WIDTH, Main.MAINMENU_WINDOW_HEIGHT);
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.setResizable(false);
        GLOBAL_MENU_STAGE = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
