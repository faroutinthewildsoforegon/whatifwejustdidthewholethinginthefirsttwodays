package com.example.server3;

import java.io.Serializable;

public class Board implements Serializable {
    char[][] map = new char[3][3];
    char lastTokenPlaced;

    public Board(){
        lastTokenPlaced = '*';
        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                map[i][j] = '*';
    }

    public Board(Board other){
        lastTokenPlaced = other.lastTokenPlaced;
        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                map[i][j] = other.map[i][j];
    }

    public Board(char[][] other){
        lastTokenPlaced = '*';
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                map[i][j] = other[i][j];
            }
        }
    }

    public void copyBoard(Board other){
        lastTokenPlaced = other.lastTokenPlaced;
        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                map[i][j] = other.map[i][j];
    }

    public boolean makeMove(char token, int row, int col){
        if(row < map.length && col < map[row].length && token != lastTokenPlaced && map[row][col] == '*'){
            map[row][col] = token;
            lastTokenPlaced = token;
            return true;
        }
        else {
            System.out.println("makeMove (" + token + " " + row + " " + col + ") invalid!");
            System.out.println("token is " + token);
            return false;
        }
    }

    public char whoWon(){
        //check rows
        for(int i = 0; i < map.length; ++i){
            if(map[i][0] != '*' && map[i][0] == map[i][1] && map[i][1] == map[i][2])
                return map[i][0];
        }
        //check columns
        for(int i = 0; i < map[0].length; ++i){
            if(map[0][i] != '*' && map[0][i] == map[1][i] && map[1][i] == map[2][i])
                return map[0][i];
        }
        //check diagonal
        if(map[0][0] != '*' && map[0][0] == map[1][1] && map[1][1] == map[2][2])
            return map[0][0];
        if(map[0][2] != '*' && map[0][2] == map[1][1] && map[1][1] == map[2][0])
            return map[0][2];

        return '*';
    }

    public boolean isWinner(){
        if(whoWon() != '*')
            return true;
        else
            return false;
    }

    public boolean isDraw(){
        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                if(map[i][j] == '*')
                    return false;
        return true;
    }

    public void resetBoard(){
        System.out.println("Resetting board");
        lastTokenPlaced = '*';
        for(int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[i].length; ++j)
                map[i][j] = '*';
    }

    public void printBoard() {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j){
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }
}