package com.example.tictactoe_v3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    public static boolean againstAI = false;
    public static AtomicBoolean shutdownRequested = new AtomicBoolean();
    public static int MAINMENU_WINDOW_WIDTH = 670;
    public static int MAINMENU_WINDOW_HEIGHT= 490;

    public static Stage GLOBAL_MENU_STAGE;
    public static Stage CURRENT_STAGE;

    public static StageController STAGE = new StageController();
    public static boolean DELETEME = false; // used to swap player/ai going first
    public static boolean gameRunning = false;


    @Override
    public void start(Stage stage) throws IOException {
        Thread t1 = new Thread(() -> {
            try{
                while(!shutdownRequested.get()) {
                    System.out.println("Thread running");
                    Thread.sleep(1000);
                }
                System.out.println("Program stopped, t1 exiting");
            } catch(InterruptedException ex) { }
        });
        t1.start();

        STAGE.initialize(stage);
        STAGE.show(0);

        /* INITIALIZE OTHER STAGES HERE */
        STAGE.addStage("PlayerVsAI2.fxml", 800, 700);
        STAGE.addStage("TwoPlayerLocal.fxml", 800, 700);
        STAGE.addStage("OnlinePlay.fxml", 800, 700);
    }



    public static void main(String[] args) { launch(); }


    @Override
    public void stop(){
        shutdownRequested.set(true);
    }
}
