package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EndgameStatusController {
    private Parent parent;
    private Scene scene;
    private Stage stage;

    @FXML
    Button returnToMainButton = new Button();
    @FXML
    Text endgameStateText = new Text();

    public void onReturnToMainButtonPressed(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root, Main.MAINMENU_WINDOW_WIDTH, Main.MAINMENU_WINDOW_HEIGHT);
        stage.setTitle("Tic Tac Toe!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
