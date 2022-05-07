package com.example.server3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerRun {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String message;
        try {
            Server server = new Server("localhost", 6969);
            while(true){
                message = in.nextLine();
                server.sendToAll(message);
            }
        } catch (IOException e) {
            System.out.println("Server setup failed");
            e.printStackTrace();
        }

    }
}
