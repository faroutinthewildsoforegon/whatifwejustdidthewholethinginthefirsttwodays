package com.example.tictactoe_v3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    public static AtomicBoolean shutdownRequested = new AtomicBoolean();
    public static int MAINMENU_WINDOW_WIDTH = 670;
    public static int MAINMENU_WINDOW_HEIGHT= 490;

    public static Stage GLOBAL_MENU_STAGE;
    public static Stage CURRENT_STAGE;

    public static StageController STAGE = new StageController();
    public static boolean DELETEME = true; // what the fuck is alex doing

    public static void dtt(){
        StageController.refreshStage(1);
    }

    public static String s = "testmylabel";
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
        STAGE.addStage("Game.fxml", 800, 700);
    }



    public static void main(String[] args) { launch(); }

    @Override
    public void stop(){
        shutdownRequested.set(true);
    }
}
