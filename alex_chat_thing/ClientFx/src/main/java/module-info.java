module com.example.clientfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clientfx to javafx.fxml;
    exports com.example.clientfx;
}