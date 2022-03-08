module com.example.javafxplayground {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxplayground to javafx.fxml;
    exports com.example.javafxplayground;
}