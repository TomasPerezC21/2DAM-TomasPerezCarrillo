module org.tomasperezpractica2.modelotableview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    // opens org.tomasperezpractica2.modelotableview to javafx.fxml;
    // exports org.tomasperezpractica2.modelotableview;


    opens org.tomasperezpractica2.modelotableview.ParteA to javafx.fxml;
    exports org.tomasperezpractica2.modelotableview.ParteA;

    opens org.tomasperezpractica2.modelotableview.ParteB to javafx.fxml;
    exports org.tomasperezpractica2.modelotableview.ParteB;
}