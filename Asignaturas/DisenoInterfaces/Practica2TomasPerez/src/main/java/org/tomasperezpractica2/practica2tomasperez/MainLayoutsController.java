package org.tomasperezpractica2.practica2tomasperez;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private TreeView dataTree = new TreeView();

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

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/org/tomasperezpractica2/practica2tomasperez/images/fichero.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ImageView folderIV = new ImageView(new Image(fis));
        folderIV.setPreserveRatio(true);
        folderIV.setFitHeight(20);

        TreeItem<String> rootItem =  new TreeItem<String>("Inbox", folderIV);
        rootItem.setExpanded(true);

        List<String> treeListItems = new ArrayList<String>(Arrays.asList("Sales", "Marketing", "Distribution", "Cost"));


        for(String item : treeListItems) {
            TreeItem<String> treeItem = new TreeItem<String>(item);
            rootItem.getChildren().add(treeItem);
        }

        dataTree.setRoot(rootItem);

    }
}
