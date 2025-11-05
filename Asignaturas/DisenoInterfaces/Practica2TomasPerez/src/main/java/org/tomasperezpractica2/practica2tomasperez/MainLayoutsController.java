package org.tomasperezpractica2.practica2tomasperez;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.cell.ComboBoxListCell;

public class MainLayoutsController {
    @FXML
    private ChoiceBox locationCB = new  ChoiceBox();

    @FXML
    private ListView qualificationsLV = new ListView();

    //Listas para qualificationsList
    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> languageCB;


    @FXML
    protected void initialize() {
        names.addAll("Objects", "Classes", "Functions", "Variables", "Compiler",
                "Debugger","Projects", "Beans", "Libraries", "Modules", "JARs");


        locationCB.setValue("Select a value...");
        locationCB.getItems().add("New York");
        locationCB.getItems().add("Orlando");
        locationCB.getItems().add(new Separator());
        locationCB.getItems().add("Madrid");

        //Inicializar listview
        for (int i = 0; i < 10; i++) {
            data.add("Indeterminate (pick a choice)");
        }

        qualificationsLV.setItems(data);
        qualificationsLV.setCellFactory(ComboBoxListCell.forListView(names));

        locationCB.setItems(FXCollections.observableArrayList("New York", "Orlando", new Separator(), "Madrid"));
        locationCB.setValue("Select a value...");

        languageCB.setValue("Select a language...");
        languageCB.getItems().addAll("Spanish", "English", "Russian", "German");


    }
}
