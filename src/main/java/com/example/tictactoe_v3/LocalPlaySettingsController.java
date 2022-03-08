package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LocalPlaySettingsController {
    private Parent parent;
    private Scene scene;
    private Stage stage;

    @FXML
    Button SinglePlayerButton = new Button();
    @FXML
    Button TwoPlayerButton = new Button();

    public void onSinglePlayerButtonPressed(ActionEvent event) throws IOException{
        System.out.println("SinglePlayerButton has been pressed");
        Parent root = FXMLLoader.load(getClass().getResource("PlayerVsAI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,600);
        stage.setTitle("Tic Tac Toe! (Local)");
        stage.setScene(scene);
        stage.setResizable(false);
        Main.GLOBAL_MENU_STAGE.hide();
        stage.show();
    }

    public void onTwoPlayerButtonPressed(ActionEvent event) throws IOException{
        System.out.println("TwoPlayerButton has been pressed");
    }

}
