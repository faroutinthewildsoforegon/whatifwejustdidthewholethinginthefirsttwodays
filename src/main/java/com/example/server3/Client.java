package com.example.server3;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

class Client {
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;


    AtomicBoolean RUNNING = new AtomicBoolean(true);

    Client(){
        try {
            socket = new Socket("localhost", 6969);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
            startReceiveThread();
            System.out.println("Connected to server");
        } catch (IOException e) {
            System.out.println("Failed to connect client to server");
            return;
        }

    }


    private void startReceiveThread(){
        Thread thread = new Thread ( () -> {
            String messageFromServer;
            while(RUNNING.get()){
                try{
                    messageFromServer = bufferedReader.readLine();
                    System.out.println(messageFromServer);
                }
                catch (Exception ex){
                    System.out.println("failed to read from server");
                }
            }
        });
        thread.start();
    }

    //requests send a majority of the time... but not all the time...
    //when the ServerConnection.startHeartbeat is commented out, it works fine.
    public boolean sendRequest(String message){
        try{
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(Exception ex){
            System.out.println("Cannot send message");
            return false;
        }
        return true;
    }
}