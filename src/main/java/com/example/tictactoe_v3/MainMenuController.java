package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.example.tictactoe_v3.Main.STAGE;

public class MainMenuController {
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
