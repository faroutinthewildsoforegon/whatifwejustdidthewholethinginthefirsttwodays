package com.example.clientfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.security.auth.login.FailedLoginException;
import java.io.*;
import java.net.Socket;

public class Controller extends Thread{

    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane sp_main;

    boolean iSentLast;

    @FXML
    public void initialize(){
        this.start();
    }


    private Client client = new Client("localhost",6969);

    public void run(){
        while (true){
            String UI_text = client.recv();
            if (UI_text == null){
                break;
            }
            addLabel(UI_text, vbox_messages);

        }
    }

    public void addLabel(String msgFromServer, VBox vBox){



        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        if(iSentLast){
            textFlow.setStyle("-fx-background-color: rgb(255,255,87); " +
                    "-fx-background-radius: 20px");
        }
        else{
            textFlow.setStyle("-fx-background-color: rgb(87,255,255); " +
                    "-fx-background-radius: 20px");
        }

        iSentLast = false;

        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });


    }


    public void on_Button_pressed(){
        iSentLast = true;
        String newMsg = tf_message.getText();
        client.send(newMsg);
        tf_message.clear();
   }

   public void on_Enter_Pressed(){
        on_Button_pressed();
   }

    class Client{
        Socket socket;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Client(String rename_this, int port){
            try{
                this.socket = new Socket(rename_this,port);
                System.out.println("Connection established.");
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }
            catch (IOException e){
                System.out.println("Error creating server.");
                e.printStackTrace();
                return;
                //System.exit(1);
                //closeEverything();
            }
        }
        void send(String in){
            try{
                bufferedWriter.write(in);
                bufferedWriter.newLine(); // Why do we need this?
                bufferedWriter.flush(); // Think I know why this.
                //System.out.println(bufferedReader.readLine());
            }
            catch (IOException e){
                System.out.println("Error in server sending message.");
                return;
            }
        }
        String recv(){
            try{
                String msg = bufferedReader.readLine();
                if (msg == null){
                    System.out.println("Nyah.");
                    return null;
                }
                else
                    return msg;
                //System.out.println(bufferedReader.readLine()); // Fix while(true)print(null); error.

            }
            catch (IOException e){
                System.out.println("Error in client receiving message.");
                return null;
                //return;
            }
        }
    }

}