module com.example.server3 {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.controlsfx.controls;

    opens com.example.server3 to javafx.fxml;
    exports com.example.server3;
}