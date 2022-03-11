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

import static com.example.tictactoe_v3.Main.STAGE;

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
    @FXML
    Button againstPlayerButton = new Button();
    @FXML
    Button againstAIButton = new Button();

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

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("LocalPlaySettings.fxml"));
//        Parent root = (Parent)loader.load();
//        scene = new Scene(root);
//        stage = new Stage();
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setTitle("title");
//        stage.show();

        againstPlayerButton.setVisible(!againstPlayerButton.isVisible());
        againstAIButton.setVisible((!againstAIButton.isVisible()));
    }
    public void onAgainstPlayerButtonPressed(ActionEvent event) throws IOException{
        System.out.println("onAgainstPlayerButtonPressed");
    }

    public void onAgainstAIButtonPressed(ActionEvent event) throws IOException{
        System.out.println("onAgainstAIButton has been pressed");


        PlayerVsAIController.player1Name.setText("PLAYER");
        PlayerVsAIController.player2Name.setText("AI");
        PlayerVsAIController.player1Score.setText("0");
        PlayerVsAIController.player2Score.setText("0");

        STAGE.stageList.get(1).setTitle("Local Play (Against AI)");
        STAGE.hide(0);
        STAGE.show(1);

//        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
//        Main.CURRENT_STAGE = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root,800,700);
//        Main.CURRENT_STAGE.setTitle("Tic Tac Toe! (Local)");
//        Main.CURRENT_STAGE.setScene(scene);
//        Main.CURRENT_STAGE.setResizable(false);
//        Main.GLOBAL_MENU_STAGE.hide();
//        Main.CURRENT_STAGE.show();
    }

    public void onOnlineButtonPressed(ActionEvent event){
        if(!Main.DELETEME)
            System.out.println("Computer goes first");
        else
            System.out.println("Playersssss goes first");

        Main.DELETEME = !Main.DELETEME;
    }


}
