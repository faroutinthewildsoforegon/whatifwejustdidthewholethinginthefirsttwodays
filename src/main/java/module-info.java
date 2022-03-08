module com.example.tictactoe_v3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_v3 to javafx.fxml;
    exports com.example.tictactoe_v3;
}