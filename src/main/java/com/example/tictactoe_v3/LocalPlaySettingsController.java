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
        Main.CURRENT_STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,600);
        Main.CURRENT_STAGE.setTitle("Tic Tac Toe! (Local)");
        Main.CURRENT_STAGE.setScene(scene);
        Main.CURRENT_STAGE.setResizable(false);
        Main.GLOBAL_MENU_STAGE.hide();
        Main.CURRENT_STAGE.show();
    }

    public void onTwoPlayerButtonPressed(ActionEvent event) throws IOException{
        System.out.println("TwoPlayerButton has been pressed");
        Parent root = FXMLLoader.load(getClass().getResource("TwoPlayerLocal.fxml"));
        Main.CURRENT_STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,600);
        Main.CURRENT_STAGE.setTitle("Tic Tac Toe! (Local)");
        Main.CURRENT_STAGE.setScene(scene);
        Main.CURRENT_STAGE.setResizable(false);
        Main.GLOBAL_MENU_STAGE.hide();
        Main.CURRENT_STAGE.show();


    }

}
