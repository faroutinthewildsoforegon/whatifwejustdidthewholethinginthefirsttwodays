package com.example.server3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class Server{
    ArrayList<ServerConnection> connectionList;
    //ArrayList<GameSessions> sessionList;
    int numberOfConnections;
    int numberOfSessions;
    ServerSocket serverSocket;

    String host;
    int port;
    AtomicBoolean RUNNING = new AtomicBoolean(true);

    Server(String host, int port) throws IOException {
        serverSocket = new ServerSocket(port);
        connectionList = new ArrayList<>();
        numberOfConnections = 0;
        numberOfSessions = 0;
        this.host = host;
        System.out.println("Server is running");
        startConnectionCheckThread();
        startTimeoutThread();
        startReceivingThread();
    }

    private void startReceivingThread() {
        Thread thread = new Thread( () -> {
            while (RUNNING.get()){
                for (int i = 0; i < connectionList.size(); ++i) {

                }
            }
        });
        thread.start();
    }

    private void startConnectionCheckThread(){
        Thread connectionCheckThread = new Thread ( () -> {
            while(RUNNING.get()) {
                try {
                    Socket newConnection = serverSocket.accept();
                    System.out.println("new Connection from: " + newConnection.getInetAddress().getHostAddress());
                    connectionList.add(new ServerConnection(connectionList.size(), newConnection));
                    System.out.println(connectionList.size() + " users connected");
                } catch (IOException e) {
                    System.out.println("server connection denied");
                    e.printStackTrace();
                }
            }
        });
        connectionCheckThread.start();
    }
    public void sendToAll(String message){
        for(int i = 0; i < connectionList.size(); ++i)
            connectionList.get(i).send(message);
    }

    public void startTimeoutThread() {
        Thread thread = new Thread ( () -> {
            while(RUNNING.get()){
                for(int i = 0; i < connectionList.size(); ++i){
                    if(!connectionList.get(i).isConnected()){
                        connectionList.get(i).stop();
                        connectionList.remove(i);
                    }
                }
            }
        });
        thread.start();
    }
}
