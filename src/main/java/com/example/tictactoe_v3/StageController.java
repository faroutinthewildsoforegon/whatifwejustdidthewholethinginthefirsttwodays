package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StageController {

    public static ArrayList<Stage> stageList;


    public static int MAINMENU_WINDOW_WIDTH = 670;
    public static int MAINMENU_WINDOW_HEIGHT= 490;

    public void initialize(Stage s) throws IOException {
        //initialize main menu
        stageList = new ArrayList<Stage>();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root, StageController.MAINMENU_WINDOW_WIDTH, StageController.MAINMENU_WINDOW_HEIGHT);
        Stage stage = s;
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.setResizable(false);
        stageList.add(stage);
    }

    public void addStage(String FXMLPath, int width, int height) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath));
        Stage stage = new Stage();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle("");
        stage.setResizable(false);
        stageList.add(stage);
    }

    public void closeAllButMain(){
        for(int i = 1; i < stageList.size(); ++i){
            hide(i);
        }
    }

    public void show(int sceneIndex){
        stageList.get(sceneIndex).show();
    }

    public void hide(int sceneIndex){
        stageList.get(sceneIndex).hide();
    }
}
