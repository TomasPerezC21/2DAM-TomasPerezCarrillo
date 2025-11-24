package org.tomasperezpractica2.modelotableview.ParteB;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import org.tomasperezpractica2.modelotableview.ParteA.DateUtil;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.Map;

public class PersonOverviewControllerMaps {

    // CAMBIO 1: La tabla ahora guarda Mapas, no Persons
    @FXML
    private TableView<Map<String, Object>> personTable;
    // CAMBIO 2: Las columnas son de Mapa a String
    @FXML
    private TableColumn<Map<String, Object>, Object> firstNameColumn;
    @FXML
    private TableColumn<Map<String, Object>, Object> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Referencia a la MainApp DE LA PARTE B
    private MainAppMaps mainApp;

    public PersonOverviewControllerMaps() {
    }

    @FXML
    private void initialize() {
        // FORMA MODERNA: Usamos una lambda en vez de MapValueFactory
        // Esto dice: "Para cada fila, coge el valor del mapa con la clave 'firstName'"
        firstNameColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().get("firstName")));
        lastNameColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().get("lastName")));

        // El resto sigue igual...
        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Rellena los labels. Ahora recibe un Map, no una Person.
     */
    private void showPersonDetails(Map<String, Object> person) {
        if (person != null) {
            // CAMBIO 4: Hay que hacer "Casting" (convertir Object a String)
            firstNameLabel.setText((String) person.get("firstName"));
            lastNameLabel.setText((String) person.get("lastName"));
            streetLabel.setText((String) person.get("street"));
            cityLabel.setText((String) person.get("city"));

            // Los números y fechas requieren cuidado especial al sacarlos del mapa
            Object postalCode = person.get("postalCode");
            postalCodeLabel.setText(postalCode != null ? postalCode.toString() : "");

            Object birthday = person.get("birthday");
            if (birthday instanceof LocalDate) {
                birthdayLabel.setText(DateUtil.format((LocalDate) birthday));
            } else {
                birthdayLabel.setText("");
            }
        } else {
            // Si es null, borrar texto
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    public void setMainApp(MainAppMaps mainApp) {
        this.mainApp = mainApp;
        // Añadir la lista de mapas a la tabla
        personTable.setItems(mainApp.getPersonData());
    }

    // --- BOTONES (De momento desactivados o simplificados hasta que hagas el Dialogo de Mapas) ---

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        // TODO: Implementar dialogo para Mapas
        System.out.println("Botón Nuevo pulsado (Falta implementar dialogo de mapas)");
    }

    @FXML
    private void handleEditPerson() {
        // TODO: Implementar dialogo para Mapas
        System.out.println("Botón Editar pulsado (Falta implementar dialogo de mapas)");
    }
}