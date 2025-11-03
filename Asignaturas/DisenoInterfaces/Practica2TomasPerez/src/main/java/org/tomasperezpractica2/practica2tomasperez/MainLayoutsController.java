package org.tomasperezpractica2.practica2tomasperez;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;

public class MainLayoutsController {
    @FXML
    private ChoiceBox locationCB = new  ChoiceBox();

    @FXML
    private ListView qualificationsLV = new ListView();


    @FXML
    protected void initialize() {
        locationCB.setValue("Select a value...");
        locationCB.getItems().add("New York");
        locationCB.getItems().add("Orlando");
        locationCB.getItems().add(new Separator());
        locationCB.getItems().add("Madrid");

        //Inicializar listview
        int i = 0;
        while (i < 10){
            qualificationsLV.getItems().add("Indeterminate (pick a choice)");
            i++;
        }

    }
}
