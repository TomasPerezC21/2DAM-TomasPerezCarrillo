package org.tomasperezpractica2.modelotableview.ParteB;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


// Importante: Importamos Map y HashMap
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class MainAppMaps extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * La lista de datos ahora guarda MAPAS, no objetos Person.
     * La clave (String) es el nombre de la columna (ej: "firstName").
     * El valor (Object) es el dato en sí.
     */
    private ObservableList<Map<String, Object>> personData = FXCollections.observableArrayList();

    public MainAppMaps() {
        // Añadimos datos de ejemplo usando un método auxiliar para no repetir código
        addSampleData("Hans", "Muster", "Calle 1", "Berlin", 1234, LocalDate.of(1999, 2, 21));
        addSampleData("Ruth", "Mueller", "Calle 2", "Munich", 5678, LocalDate.of(2000, 5, 15));
        addSampleData("Heinz", "Kurz", "Calle 3", "Hamburgo", 9012, LocalDate.of(1985, 1, 1));
        addSampleData("Cornelia", "Meier", "Calle 4", "Frankfurt", 3456, LocalDate.of(1990, 7, 30));
    }

    /**
     * Método auxiliar para crear el Mapa rápidamente
     */
    private void addSampleData(String firstName, String lastName, String street, String city, int postalCode, LocalDate birthday) {
        Map<String, Object> person = new HashMap<>();
        person.put("firstName", firstName);
        person.put("lastName", lastName);
        person.put("street", street);
        person.put("city", city);
        person.put("postalCode", postalCode);
        person.put("birthday", birthday);

        personData.add(person);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tomás Pérez - Versión MAPAS");

        // Icono (Asegúrate de que la ruta de la imagen sea correcta)
        try {
            this.primaryStage.getIcons().add(
                    new Image(getClass().getResourceAsStream("/images/location_2149438.png"))
            );
        } catch (Exception e) {
            System.out.println("No se pudo cargar el icono.");
        }

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            // Asegúrate de que rootLayout.fxml existe en este paquete o ajusta la ruta
            loader.setLocation(MainAppMaps.class.getResource("rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            // Carga el FXML de la vista general
            loader.setLocation(MainAppMaps.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            // Obtenemos el controlador NUEVO (el de los mapas)
            // Asegúrate de que en el FXML el fx:controller apunte a PersonOverviewControllerMaps
            PersonOverviewControllerMaps controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre el diálogo de edición para un MAPA.
     */
    public boolean showPersonEditDialog(Map<String, Object> person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppMaps.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person (Map Version)");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Necesitarás un controlador de dialogo que acepte Mapas
            PersonEditDialogControllerMaps controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPersonMap(person);

            dialogStage.showAndWait();

            // return controller.isOkClicked();
            return false; // Temporal hasta que hagas el controlador del diálogo

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Devuelve la lista de Mapas
     */
    public ObservableList<Map<String, Object>> getPersonData() {
        return personData;
    }
}