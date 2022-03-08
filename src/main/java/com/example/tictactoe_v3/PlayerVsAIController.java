package com.example.tictactoe_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerVsAIController {

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
    @FXML
    Button exitButton = new Button();

    Seed[][] board = new Seed[3][3];
    Boolean player = true;
    Boolean gameRunning = false;
    Image xImage = new Image(getClass().getResourceAsStream("x2.png"));
    Image oImage = new Image(getClass().getResourceAsStream("o2.png"));

    ImageView tLeft = new ImageView();
    ImageView tCent = new ImageView();
    ImageView tRight = new ImageView();
    ImageView mLeft = new ImageView();
    ImageView mCent = new ImageView();
    ImageView mRight = new ImageView();
    ImageView bLeft = new ImageView();
    ImageView bCent = new ImageView();
    ImageView bRight = new ImageView();

    //for testing:
    Seed tboard[][] =
            {
                    { Seed.X, Seed.EMPTY, Seed.O},
                    { Seed.EMPTY, Seed.X, Seed.EMPTY},
                    { Seed.O, Seed.O, Seed.O}
            };



    public void startGame(){
        // for debugging
        System.out.println("Board value is: " + evaluate());

        gameRunning = true;
        player = false;
        initBoard();
        printBoardToConsole();
        updateStatusLabel(player);
        if(Main.DELETEME) {
            dumbMoveComputer();
        }
        player = !player;
    }


    public void onButtonPressed(ActionEvent event) {
        System.out.println(event.getSource());
        String buttonFxIdStr = getFxIDFromButton(event.getSource().toString());
        System.out.println(buttonFxIdStr);

        if(gameRunning){
            if (isMoveValid(buttonFxIdStr)){

                makeMove(player, buttonFxIdStr);
                // at the end of a successful turn, the player changes

                // check if move results in win
                if(gameRunning && checkForWin(player)){
                    System.out.println("Player" + (player ? " 1 " : " 2 ") + "has won!" );
                    statusLabel.setText("Player" + (player ? " 1 " : " Computer ") + "has won!" );
                    gameRunning = false;
                }

                // check if results in draw
                if(gameRunning && checkForDraw()){
                    statusLabel.setText("It's a draw!");
                    System.out.println("It's a draw!");
                    gameRunning = false;
                }

                // change player
                player = !player;
                updateStatusLabel(player);

                if(gameRunning){
                    dumbMoveComputer();
                    // check if move results in win
                    if(gameRunning && checkForWin(player)){
                        System.out.println("Player" + (player ? " 1 " : " 2 ") + "has won!" );
                        statusLabel.setText("Player" + (player ? " 1 " : " Computer ") + "has won!" );
                        gameRunning = false;
                    }

                    // check if results in draw
                    if(gameRunning && checkForDraw()){
                        statusLabel.setText("It's a draw!");
                        System.out.println("It's a draw!");
                        gameRunning = false;
                    }

                    player = !player;
                }
            }



        }
        else{
            statusLabel.setText("The game must be running for a move to be entered.");
            System.out.println("The game must be running for a move to be entered.");
        }


    }

    public void onExitButtonPressed(ActionEvent event)throws IOException{
        Main.CURRENT_STAGE.close();
        Main.GLOBAL_MENU_STAGE.show();
    }


    // plays a move from top to bottom, if a space is not occupied, the move will be taken.
    public void dumbMoveComputer(){
        System.out.println("Computer Move called!!");
        /*
        String[][] possibleMoves = {{"topLeft","midLeft","botLeft"},
                                    {"topCent","midCent","botCent"},
                                    {"topRight","midRight","botRight"}};

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(isMoveValid(possibleMoves[i][j])){
                    makeMove(player,possibleMoves[i][j]);
                    return;
                }
            }
        }
        */

        int[] bestMove = findOptimalPlay();
        System.out.println("The optimal move is: ");
        System.out.println("Row:" + bestMove[0] + " Col: " + bestMove[1]);
        makeMove(player,getMoveNameFromRowCol(bestMove[0],bestMove[1]));

    }

    public int[] findOptimalPlay(){
        // TODO: replace with min
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove= {-1, -1};

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){

                // check if cell is empty
                if(isMoveValid(i,j)){
                    // get the name of that move; no longer necessary
                    // String tempMov = getMoveNameFromRowCol(i,j);

                    // make the move
                    board[i][j] = Seed.O;

                    // compute eval for move
                    System.out.println("minimax eval: " + minimax(0,true));    // this currently does not return anything other than 1000.
                    int moveVal = minimax(0,false);
                    System.out.println("Move Val: " + moveVal);

                    // undo move
                    board[i][j] = Seed.EMPTY;

                    // if the move we just checked is better, take it
                    if(moveVal > bestVal){
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }

                }
            }
        }

        System.out.printf("The value of the best Move is : %d\n\n", bestVal);
        return bestMove;
    }

    // returns 10 is winning board is O, and negative 10 if winning board is X, otherwise returns 0.
    public int evaluate(){

        // checking rows for X or 0 victory
        for(int i = 0; i < 3; i++){
            if(board[i][0] == board[i][1] &&
               board[i][1] == board[i][2]){
                if(board[i][0] == Seed.O)
                    return 10;
                else if(board[i][0] == Seed.X)
                    return -10;
            }
        }

        // checking cols for X or O victory
        for(int j = 0; j < 3; j++){
            if(board[0][j] == board[1][j] &&
               board[1][j] == board[2][j]){
                if (board[0][j] == Seed.O)
                    return 10;
                else if(board[0][j] == Seed.X)
                    return -10;
            }
        }

        // checking diagonals
        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if (board[0][0] == Seed.O)
                return +10;
            else if (board[0][0] == Seed.X)
                return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if (board[0][2] == Seed.O)
                return +10;
            else if (board[0][2] == Seed.X)
                return -10;
        }

        // else if none of them have won return 0
        return 0;
    }

    public int minimax(int depth, Boolean isMax){
        System.out.println("Depth is: " + depth);
        int score = evaluate();
        if(depth == 0){         // if maximizer has won return score
            if (score == 10)
                return 1000;
            // if minimizer has lost return score
            if (score == -10)
                return -1000;
        }
        else{
            // if maximizer has won return score
            if (score == 10)
                return score;
            // if minimizer has lost return score
            if (score == -10)
                return score;
        }
        // if no more moves and no winner, return 0 for draw
        if(checkForDraw() == true){
            return 0;
        }

        if(isMax){
            int best = Integer.MIN_VALUE;

            // traverse all cells
            for(int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    // check if current cell is empty
                    if(board[i][j] == Seed.EMPTY){
                        // if so make the move
                        board[i][j] = Seed.O;
                        // choose max value from recursive call
                        best = Math.max(best,minimax(depth + 1, !isMax));
                        // undo move made
                        board[i][j] = Seed.EMPTY;
                    }
                }
            }
            return best;
        }
        // otherwise, it's the minimizer's turn
        else{
            int best = Integer.MAX_VALUE;

            // traverse all cells
            for(int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    // check if current cell is empty
                    if(board[i][j] == Seed.EMPTY){
                        // if so make the move
                        board[i][j] = Seed.X;
                        // choose max value from recursive call
                        best = Math.min(best,minimax(depth + 1, !isMax));
                        // undo move made
                        board[i][j] = Seed.EMPTY;
                    }
                }
            }
            return best;
        }
    }

    public String getFxIDFromButton(String eventString){
        Pattern pattern = Pattern.compile("(?<=\\=)(.*?)(?=\\,)");
        Matcher matcher = pattern.matcher(eventString);
        if(matcher.find()){
            return matcher.group();
        }
        else return "sorry";
    }

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

    public String getMoveNameFromRowCol(int row, int col){

        if(row == 0 && col == 0){return "topLeft";};
        if(row == 0 && col == 1){return "topCent";};
        if(row == 0 && col == 2){return "topRight";};
        if(row == 1 && col == 0){return "midLeft";};
        if(row == 1 && col == 1){return "midCent";};
        if(row == 1 && col == 2){return "midRight";};
        if(row == 2 && col == 0){return "botLeft";};
        if(row == 2 && col == 1){return "botCent";};
        if(row == 2 && col == 2){return "botRight";};
        return "no possible move";

    }

    public void makeMove(Boolean player,String fxId){


        switch (fxId){
            case "topLeft":
                if(player){
                    tLeft.setImage(xImage);
                    tLeft.setFitHeight(80);
                    tLeft.setFitWidth(80);
                    topLeft.setGraphic(tLeft);

                    board[0][0] = Seed.X;
                    try{ onEndGame(); }
                    catch(Exception e){}
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

    public void onEndGame() throws IOException{
        if(checkForWin(player)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndgameStatus.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Winner!");
            stage.show();
        }
    }

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

    // This version just takes the coordinates if that's what you need
    public Boolean isMoveValid(int row, int col){

        if (board[row][col] == Seed.EMPTY){
            System.out.println("["+row+"],["+col+"] is valid!");
            return true;
        } else{
            System.out.println("["+row+"],["+col+"] is invalid and occupied by " + board[row][col]);
            return false;
        }
    }

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

    public void printBoardToConsole(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void updateStatusLabel(Boolean player){

        if(player){
            statusLabel.setText("Player 1's turn");
        }
        else {
            statusLabel.setText("Player's turn");
        }

    }
}
