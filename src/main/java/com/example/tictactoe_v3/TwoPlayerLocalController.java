package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoPlayerLocalController {

    // Butt ton of buttons now bbbbbbbbbbbbb
    @FXML
    Button topLeft = new Button();
    @FXML
    Button topCent = new Button();
    @FXML
    Button topRight = new Button();
    @FXML
    Button midLeft = new Button();
    @FXML
    Button midCent = new Button();
    @FXML
    Button midRight = new Button();
    @FXML
    Button botLeft = new Button();
    @FXML
    Button botCent = new Button();
    @FXML
    Button botRight = new Button();
    @FXML
    Button startButton = new Button();
    @FXML
    Button printBoardButton = new Button();
    @FXML
    Label statusLabel = new Label();

    Seed[][] board = new Seed[3][3];                // The underlying board, *see Seed.java*
    Boolean player = true;                          // true = player 1, x . false = player 2, o
    Boolean gameRunning = false;                    // self-explanatory; checks game state
    //Images imported for x and o pictures
    Image xImage = new Image(getClass().getResourceAsStream("x2.png"));
    Image oImage = new Image(getClass().getResourceAsStream("o2.png"));

    // This is so dumb, but I had to do it to fix a bug.
    ImageView tLeft = new ImageView();
    ImageView tCent = new ImageView();
    ImageView tRight = new ImageView();
    ImageView mLeft = new ImageView();
    ImageView mCent = new ImageView();
    ImageView mRight = new ImageView();
    ImageView bLeft = new ImageView();
    ImageView bCent = new ImageView();
    ImageView bRight = new ImageView();


    // activates when start button is clicked in UI
    public void startGame(){
        gameRunning = true;
        player = true;
        initBoard();
        printBoardToConsole();
        updateStatusLabel(player);
    }

    // activates whenever a player makes a move.
    public void onButtonPressed(ActionEvent event) {
        System.out.println(event.getSource());
        String buttonFxIdStr = getFxIDFromButton(event.getSource().toString());
        System.out.println(buttonFxIdStr);

        if(gameRunning){
            if (isMoveValid(buttonFxIdStr)){

                makeMove(player, buttonFxIdStr);
                // at the end of a successful turn, the player changes
                player = !player;
                updateStatusLabel(player);
                //AImove();
            }
            else {
                System.out.println("sorry that move is not valid.");
            }

            // player has to be negated while being passed because it's logically flipped after a move is entered.
            // note: this could be changed to go inside the player move before player is changed.
            if(gameRunning && checkForWin(!player)){
                System.out.println("Player" + (!player ? " 1 " : " 2 ") + "has won!" );
                statusLabel.setText("Player" + (!player ? " 1 " : " 2 ") + "has won!" );
                gameRunning = false;
            }

            if(gameRunning && checkForDraw()){
                statusLabel.setText("It's a draw!");
                System.out.println("It's a draw!");
                gameRunning = false;
            }
        }
        else{
            statusLabel.setText("The game must be running for a move to be entered.");
            System.out.println("The game must be running for a move to be entered.");
        }


    }
// Piece of min-max     returns point value of a moveset
/*
    public int evaluate(Seed[][] b){
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }
*/
    // Converts the info given from event into shorter usable string
    // eg. input: Button[id=midRight, styleClass=button]'' -> output: midRight
    public String getFxIDFromButton(String eventString){
        Pattern pattern = Pattern.compile("(?<=\\=)(.*?)(?=\\,)");
        Matcher matcher = pattern.matcher(eventString);
        if(matcher.find()){
            return matcher.group();
        }
        else return "sorry";
    }

    // takes a string from getFxIDFromButton() and returns a two integer array that contains the relative positions of
    // the buttons and the content in the 2d board array.
    public int[] getPosition(String fxId){
        // row, col
        int[] coordinates = new int[2];

        switch (fxId){
            case "topLeft":     {coordinates[0] = 0; coordinates[1] = 0; break;}
            case "topCent":     {coordinates[0] = 0; coordinates[1] = 1; break;}
            case "topRight":    {coordinates[0] = 0; coordinates[1] = 2; break;}
            case "midLeft":     {coordinates[0] = 1; coordinates[1] = 0; break;}
            case "midCent":     {coordinates[0] = 1; coordinates[1] = 1; break;}
            case "midRight":    {coordinates[0] = 1; coordinates[1] = 2; break;}
            case "botLeft":     {coordinates[0] = 2; coordinates[1] = 0; break;}
            case "botCent":     {coordinates[0] = 2; coordinates[1] = 1; break;}
            case "botRight":    {coordinates[0] = 2; coordinates[1] = 2; break;}
        }

        return coordinates;
    }

    // TODO: this is silly and should be rewritten later.
    // Pre-condition: player move received from fxId String is valid
    // Takes string from getFxIDFromButton() and the current player and then updates UI and underlying game board.
    public void makeMove(Boolean player,String fxId){

        switch (fxId){
            case "topLeft":
                if(player){
                    tLeft.setImage(xImage);
                    tLeft.setFitHeight(80);
                    tLeft.setFitWidth(80);
                    topLeft.setGraphic(tLeft);

                    board[0][0] = Seed.X;
                }
                else{
                    tLeft.setImage(oImage);
                    tLeft.setFitHeight(80);
                    tLeft.setFitWidth(80);
                    topLeft.setGraphic(tLeft);

                    board[0][0] = Seed.O;
                }
                break;
            case "topCent":
                if(player){
                    tCent.setImage(xImage);
                    tCent.setFitHeight(80);
                    tCent.setFitWidth(80);
                    topCent.setGraphic(tCent);

                    board[0][1] = Seed.X;
                }
                else{
                    tCent.setImage(oImage);
                    tCent.setFitHeight(80);
                    tCent.setFitWidth(80);
                    topCent.setGraphic(tCent);

                    board[0][1] = Seed.O;
                }
                break;
            case "topRight":
                if(player){
                    tRight.setImage(xImage);
                    tRight.setFitHeight(80);
                    tRight.setFitWidth(80);
                    topRight.setGraphic(tRight);

                    board[0][2] = Seed.X;
                }
                else{
                    tRight.setImage(oImage);
                    tRight.setFitHeight(80);
                    tRight.setFitWidth(80);
                    topRight.setGraphic(tRight);

                    board[0][2] = Seed.O;
                }
                break;

            case "midLeft":
                if(player){
                    mLeft.setImage(xImage);
                    mLeft.setFitHeight(80);
                    mLeft.setFitWidth(80);
                    midLeft.setGraphic(mLeft);

                    board[1][0] = Seed.X;
                }
                else{
                    mLeft.setImage(oImage);
                    mLeft.setFitHeight(80);
                    mLeft.setFitWidth(80);
                    midLeft.setGraphic(mLeft);

                    board[1][0] = Seed.O;
                }
                break;
            case "midCent":
                if(player){
                    mCent.setImage(xImage);
                    mCent.setFitHeight(80);
                    mCent.setFitWidth(80);
                    midCent.setGraphic(mCent);

                    board[1][1] = Seed.X;
                }
                else{
                    mCent.setImage(oImage);
                    mCent.setFitHeight(80);
                    mCent.setFitWidth(80);
                    midCent.setGraphic(mCent);

                    board[1][1] = Seed.O;
                }
                break;
            case "midRight":
                if(player){
                    mRight.setImage(xImage);
                    mRight.setFitHeight(80);
                    mRight.setFitWidth(80);
                    midRight.setGraphic(mRight);

                    board[1][2] = Seed.X;
                }
                else{
                    mRight.setImage(oImage);
                    mRight.setFitHeight(80);
                    mRight.setFitWidth(80);
                    midRight.setGraphic(mRight);

                    board[1][2] = Seed.O;
                }
                break;

            case "botLeft":
                if(player){
                    bLeft.setImage(xImage);
                    bLeft.setFitHeight(80);
                    bLeft.setFitWidth(80);
                    botLeft.setGraphic(bLeft);

                    board[2][0] = Seed.X;
                }
                else{
                    bLeft.setImage(oImage);
                    bLeft.setFitHeight(80);
                    bLeft.setFitWidth(80);
                    botLeft.setGraphic(bLeft);

                    board[2][0] = Seed.O;
                }
                break;
            case "botCent":
                if(player){
                    bCent.setImage(xImage);
                    bCent.setFitHeight(80);
                    bCent.setFitWidth(80);
                    botCent.setGraphic(bCent);

                    board[2][1] = Seed.X;
                }
                else{
                    bCent.setImage(oImage);
                    bCent.setFitHeight(80);
                    bCent.setFitWidth(80);
                    botCent.setGraphic(bCent);

                    board[2][1] = Seed.O;
                }
                break;
            case "botRight":
                if(player){
                    bRight.setImage(xImage);
                    bRight.setFitHeight(80);
                    bRight.setFitWidth(80);
                    botRight.setGraphic(bRight);

                    board[2][2] = Seed.X;
                }
                else{
                    bRight.setImage(oImage);
                    bRight.setFitHeight(80);
                    bRight.setFitWidth(80);
                    botRight.setGraphic(bRight);

                    board[2][2] = Seed.O;
                }
                break;

        }

    }

    // function returns true if the board content at the index location is not modified (SEED.EMPTY), false if something
    // in the 2D array of seeds (board) is an enumeration SEED.X or SEED.O.
    // (eg. it only returns true if the queried coordinates are )
    public Boolean isMoveValid(String moveLocation){

        int[] position = getPosition(moveLocation);

        if (board[position[0]][position[1]] == Seed.EMPTY){
            System.out.println("["+position[0]+"],["+position[1]+"] is valid!");
            return true;
        } else{
            System.out.println("["+position[0]+"],["+position[1]+"] is invalid and occupied by " + board[position[0]][position[1]]);
            return false;
        }
    }

    // if no other spaces on the board can be filled it's a draw.
    public Boolean checkForDraw(){
        for(int i = 0; i < 3; i++ ){
            for(int j = 0; j < 3; j++){
                if (board[i][j] == Seed.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    // a hideous function that checks all win conditions for either player
    // returns true if that player has 3 in a row.
    public Boolean checkForWin(boolean player){
        Seed winningSeed;

        if(player){
            winningSeed = Seed.X;
        }
        else{
            winningSeed = Seed.O;
        }

        return( // checks verticals
                board[0][0] == winningSeed && board[1][0] == winningSeed && board[2][0] == winningSeed ||
                board[0][1] == winningSeed && board[1][1] == winningSeed && board[2][1] == winningSeed ||
                board[0][2] == winningSeed && board[1][2] == winningSeed && board[2][2] == winningSeed ||
                // checks horizontals
                board[0][0] == winningSeed && board[0][1] == winningSeed && board[0][2] == winningSeed ||
                board[1][0] == winningSeed && board[1][1] == winningSeed && board[1][2] == winningSeed ||
                board[2][0] == winningSeed && board[2][1] == winningSeed && board[2][2] == winningSeed ||
                // diagonal top left to bot right
                board[0][0] == winningSeed && board[1][1] == winningSeed && board[2][2] == winningSeed ||
                // diagonal bottom left to top right
                board[2][0] == winningSeed && board[1][1] == winningSeed && board[0][2] == winningSeed
                );

    }

    // startup function that clears the board.
    public void initBoard(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = Seed.EMPTY;
            }
        }

        topLeft.setGraphic(null);
        topCent.setGraphic(null);
        topRight.setGraphic(null);
        midLeft.setGraphic(null);
        midCent.setGraphic(null);
        midRight.setGraphic(null);
        botLeft.setGraphic(null);
        botCent.setGraphic(null);
        botRight.setGraphic(null);
    }

    // debugging tool:
    // TODO: delete this later
    public void printBoardToConsole(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    // Changes the statusLabel based on which player's turn it is.
    public void updateStatusLabel(Boolean player){

        if(player){
            statusLabel.setText("Player 1's turn");
        }
        else {
            statusLabel.setText("Player 2's turn");
        }

    }
}
