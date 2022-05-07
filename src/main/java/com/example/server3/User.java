package com.example.server3;

import java.io.*;
import java.net.Socket;

public class User {
    private String ip;
    Socket socket;
    DataOutputStream toServer;
    DataInputStream fromServer;
    ObjectOutputStream objectToServer;
    ObjectInputStream objectFromServer;

    public User(Socket s) throws IOException {
        socket = s;
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new DataInputStream(socket.getInputStream());
        ip = socket.getInetAddress().getHostAddress();
    }

}
