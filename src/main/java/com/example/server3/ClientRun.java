package com.example.server3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ClientRun {
    public static void main(String[] args) {
        Client c = new Client();
        Scanner in = new Scanner(System.in);
        String s;
        while(true){
            if(c.sendRequest(in.nextLine()))
                System.out.println("message sent");
            else{
                System.out.println("message failed");
            }
        }
    }
}
