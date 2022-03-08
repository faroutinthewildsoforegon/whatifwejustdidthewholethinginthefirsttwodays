package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;


public class MainMenuController {

    /* New Stuff that I hope works */
    private Parent parent;
    private Scene scene;
    private Stage stage;

    @FXML
    Button playLocalButton = new Button();
    @FXML
    Button playOnlineButton = new Button();
    @FXML
    Label welcomeLabel = new Label();

    public void onLocalButtonPressed(ActionEvent event)throws IOException{
        /*
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LocalTicTacToe.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Tic Tac Toe! (Local)");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
         */

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LocalPlaySettings.fxml"));
        Parent root = (Parent)loader.load();
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("title");
        stage.show();
    }

    public void onOnlineButtonPressed(ActionEvent event){
        welcomeLabel.setText("Online is not ready");
    }


}
