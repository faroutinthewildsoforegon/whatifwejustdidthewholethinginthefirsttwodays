//package com.example.tictactoe_v3;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class StageController {
//
//    public static ArrayList<Stage> stageList;
//    Parent root;
//    Scene scene;
//    Stage stage;
//
//    public static int MAINMENU_WINDOW_WIDTH = 670;
//    public static int MAINMENU_WINDOW_HEIGHT= 490;
//
//    public void initialize(Stage s) throws IOException {
//        //initialize main menu
//        stageList = new ArrayList<Stage>();
//        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        scene = new Scene(root, StageController.MAINMENU_WINDOW_WIDTH, StageController.MAINMENU_WINDOW_HEIGHT);
//        stage = s;
//        stage.setTitle("Tic Tac Toe!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stageList.add(stage);
//    }
//
//    public void addStage(String FXMLPath, int width, int height) throws IOException{
//        root = FXMLLoader.load(getClass().getResource(FXMLPath));
//        stage = new Stage();
//        scene = new Scene(root, width, height);
//        stage.setTitle("");
//        stage.setResizable(false);
//        stageList.add(stage);
//        stage.show();
//    }
//
//    public void closeAllButMain(){
//        for(int i = 1; i < stageList.size(); ++i){
//            hide(i);
//        }
//    }
//
//    public void show(int sceneIndex){
//        stageList.get(sceneIndex).show();
//    }
//
//    public void hide(int sceneIndex){
//        stageList.get(sceneIndex).hide();
//    }
//}
