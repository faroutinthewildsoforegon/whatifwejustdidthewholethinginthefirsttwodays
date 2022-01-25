module com.weebaholics.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.weebaholics.tictactoe to javafx.fxml;
    exports com.weebaholics.tictactoe;
}