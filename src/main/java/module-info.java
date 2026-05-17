module com.example.ejerciciosarchivos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejerciciosarchivos to javafx.fxml;
    exports com.example.ejerciciosarchivos;
}