module org.tomasperezpractica2.practica2tomasperez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.tomasperezpractica2.practica2tomasperez to javafx.fxml;
    exports org.tomasperezpractica2.practica2tomasperez;
}