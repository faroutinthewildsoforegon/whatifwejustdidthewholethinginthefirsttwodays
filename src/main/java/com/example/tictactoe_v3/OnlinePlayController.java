package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlinePlayController {
    @FXML
    Button topLeft = new Button();
    @FXML
    Button topCent = new Button();
    @FXML
    Button topRight = new Button();
    @FXML
    Button midLeft = new Button();
    @FXML
    Button midCent = new Button();
    @FXML
    Button midRight = new Button();
    @FXML
    Button botLeft = new Button();
    @FXML
    Button botCent = new Button();
    @FXML
    Button botRight = new Button();
    public void onButtonPressed(ActionEvent event) throws IOException{

    }

    @FXML
    VBox vboxMessageList = new VBox();

    @FXML
    Button returnToMainButton = new Button();
    public void onReturnToMainButtonPressed(ActionEvent event) throws IOException{
        System.out.println("return to main button pressed");
        Main.STAGE.show(0);
        Main.STAGE.hide(3);
    }
    @FXML
    Button restartButton = new Button();
    public void onRestartButtonPressed(){

    }

    @FXML
    TextField chatMessageInput = new TextField();
    public void on_Enter_Pressed(ActionEvent event) throws IOException{

    }

    @FXML
    Button sendMessageButton = new Button();
    public void onSendMessageButtonPressed(ActionEvent event) throws IOException{

    }


}
