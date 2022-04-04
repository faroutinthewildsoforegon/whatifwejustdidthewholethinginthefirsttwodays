package com.example.tictactoe_v3;

import java.math.BigInteger;

public class MyThread extends Thread{
    @Override
    public void run(){
        BigInteger b = new BigInteger("1");
        while(true){
            System.out.println(b);
            b = b.add(new BigInteger("1"));
        }
    }





}
