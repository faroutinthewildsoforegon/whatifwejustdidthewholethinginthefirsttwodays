package com.example.server3;

import java.util.Scanner;

public class ConsoleLocalTicTacToe {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner in = new Scanner(System.in);
        int row, col;
        char token = 'o';
        while (true) {
            board.printBoard();
            System.out.println("Enter row and column");
            row = in.nextInt();
            col = in.nextInt();
            if (board.makeMove(token, row, col)) {
                token = tokenSwap(token);
                if(board.isWinner()){
                    System.out.println("o wins!");
                    board.resetBoard();
                    token = 'o';
                }
                if(board.isDraw()){
                    System.out.println("Draw!");
                    board.resetBoard();
                    token = 'o';
                }
            }
        }
    }
    public static char tokenSwap(char token){
        if(token == 'o')
            return 'x';
        else if(token == 'x')
            return 'o';
        else
            return '*';
    }
}
