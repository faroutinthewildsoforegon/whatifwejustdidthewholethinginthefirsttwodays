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
    public static Stage CURRENT_STAGE;
    public static boolean DELETEME = true; // what the fuck is alex doing
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root, Main.MAINMENU_WINDOW_WIDTH, Main.MAINMENU_WINDOW_HEIGHT);
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.setResizable(false);
        GLOBAL_MENU_STAGE = stage;
        GLOBAL_MENU_STAGE.show();
    }

    public static void main(String[] args) { launch(); }
}
