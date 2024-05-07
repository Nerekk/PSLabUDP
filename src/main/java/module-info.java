module com.example.pslabudp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pslabudp to javafx.fxml;
    exports com.example.pslabudp;
}