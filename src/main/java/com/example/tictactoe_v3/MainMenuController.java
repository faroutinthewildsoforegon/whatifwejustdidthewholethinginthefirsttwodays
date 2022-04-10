package com.example.tictactoe_v3;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
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
import java.util.Observable;
import java.util.Observer;

import static com.example.tictactoe_v3.Main.STAGE;

public class MainMenuController {

    private Parent parent;
    private Scene scene;
    private Stage stage;

    static String p1n;
    static String p2n;

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
        showHideLocalPlayOptions();
    }
    public void onAgainstPlayerButtonPressed(ActionEvent event) throws IOException{
        System.out.println("onAgainstPlayerButtonPressed");
        STAGE.stageList.get(1).setTitle("Local Play (Against Player)");
        STAGE.hide(0);
        STAGE.show(1);
    }

    public void onAgainstAIButtonPressed(ActionEvent event) throws IOException{
        System.out.println("onAgainstAIButton has been pressed");
        if(ifLocalPlayOptionsVisable())
            showHideLocalPlayOptions();
        STAGE.stageList.get(1).setTitle("Local Play (Against AI)");
        STAGE.hide(0);
        STAGE.show(1);
    }

    public void onOnlineButtonPressed(ActionEvent event){
        if(!Main.DELETEME)
            System.out.println("Computer goes first");
        else
            System.out.println("Playersssss goes first");

        Main.DELETEME = !Main.DELETEME;
    }
    public void showHideLocalPlayOptions(){
        againstPlayerButton.setVisible(!againstPlayerButton.isVisible());
        againstAIButton.setVisible((!againstAIButton.isVisible()));
    }

    public boolean ifLocalPlayOptionsVisable(){
        return againstPlayerButton.isVisible();
    }

}
