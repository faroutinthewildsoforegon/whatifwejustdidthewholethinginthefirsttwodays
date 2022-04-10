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
                // Do some other stuff? ~~Alex.
                break;
            }
            //addLabel(UI_text, vbox_messages);
            getMessage(UI_text);

        }
    }

    void getMessage(String msg){
        addLabel(msg,vbox_messages);
    }

    public void addLabel(String msgFromServer, VBox vBox){



        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        if(iSentLast){
            //textFlow.setStyle("-fx-background-color: rgb(255,255,87); " +
            textFlow.setStyle("-fx-background-color: rgb("+red+","+blue+","+green+"); " +
                    "-fx-background-radius: 20px"); // Eric, please make the text the inverse of the background colors.  ~~Alex.
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

    private int red = 255;
    private int blue = 255;
    private int green = 87;
    void setColors(String str){
        java.util.Scanner in = new java.util.Scanner(str);
        try {
            red = in.nextInt();
            blue = in.nextInt();
            green = in.nextInt();
        }
        catch(Exception e){
            System.out.println("Invalid color input isn't the end of the world.");
        }
    }

    public void on_Button_pressed(){
        this.sendMessage();
    }

    public void on_Enter_Pressed(){
        this.sendMessage();
    }

    public void sendMessage(){
        iSentLast = true;
        String newMsg = tf_message.getText();
        if (newMsg.startsWith("CLIENT:")){
            if (newMsg.startsWith("CLIENT: DISCONNECT")) {
                client.seppuku();
            }
            else if (newMsg.startsWith("CLIENT: HELP")){
                //System.out.println("DISCONNECT, HELP, SETCOLOR, STATECOLORS");
                getMessage("DISCONNECT, HELP, SETCOLOR, STATECOLORS."); // Find a better, more maintainable way to do this. ~~Alex
            }
            else if (newMsg.startsWith("CLIENT: SETCOLOR ")){
                setColors(newMsg.substring(16));
            }
            else if (newMsg.startsWith("CLIENT: STATECOLORS")){
                String colors = "R:"+red+", B:"+blue+", G:"+green+".";
                //String colors = java.util.HexFormat.of().toHexDigits(red).substring(6).toUpperCase()+String.format("0x%02X",green)+String.format("0x%02X",blue)+".";
                getMessage(colors);
                colors = java.util.HexFormat.of().toHexDigits((red*256 + blue)*256 + green).substring(2).toUpperCase()+".";
                //System.out.println(colors);
                getMessage(colors);
            }
        }
        else {
            client.send(newMsg);
        }
        tf_message.clear();
   }



    class Client{
        void seppuku() { // I used `seppuku` because `close` I think is used for some other things, or deprecated or something.
            System.out.println("Client tried to kill itself but I don't actually know how to do that since it's a GUI thing so here's an error message instead.");
        }
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
                System.out.println("Error in client sending message.");
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