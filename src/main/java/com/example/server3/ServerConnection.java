package com.example.server3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerConnection extends Thread{
    final int connectionID;
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    AtomicBoolean RUNNING = new AtomicBoolean(true);

    ServerConnection(int connectionID, Socket socket) {
        this.connectionID = connectionID;
        this.socket = socket;
        try{
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        this.start();
        System.out.println("ServerConnection created with ID: " + connectionID);
        startHeartbeat();
        startReceiveThread();
    }

    public boolean send(String message){
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(Exception ex){
            System.out.println("client not connected");
            return false;
        }
        return true;
    }

    private void startReceiveThread(){
        Thread thread = new Thread ( () -> {
            String messageFromClient;
            while(RUNNING.get()){
                try{
                    messageFromClient = bufferedReader.readLine();
                    System.out.println(messageFromClient);
                }
                catch(Exception ex){
                    System.out.println("client disconnect");
                }
            }
        });
        thread.start();
    }



    public boolean isConnected() {
        return RUNNING.get();
    }

    private void startHeartbeat() {
        Thread thread = new Thread ( () -> {
            String check;
            while(RUNNING.get()){
                try {
                    check = bufferedReader.readLine();
                    if(check == null) {
                        RUNNING.set(false);
                        System.out.println("connection to client lost");
                    }
                    sleep(5000);
                }
                catch(Exception ex){
                    //this should never run unless big mistake.
                    RUNNING.set(false);
                    System.out.println("connection to client lost");
                }
            }
        });
        thread.start();
    }
}
