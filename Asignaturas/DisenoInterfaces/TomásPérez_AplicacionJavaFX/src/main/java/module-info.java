module org.example.tutorial1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tutorial1 to javafx.fxml;
    exports org.example.tutorial1;
}