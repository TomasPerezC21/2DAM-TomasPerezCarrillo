package org.tomasperezpractica2.modelotableview.ParteB;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tomasperezpractica2.modelotableview.ParteA.DateUtil;

// CAMBIO: Importamos Map y LocalDate
import java.time.LocalDate;
import java.util.Map;

/**
 * Dialog to edit details of a person (MAP VERSION).
 */
public class PersonEditDialogControllerMaps {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;

    // CAMBIO 1: El objeto a editar ahora es un Mapa, no una Person
    private Map<String, Object> personMap;

    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * CAMBIO 2: Este método sustituye a setPerson.
     * Recibe el Mapa y rellena los campos de texto.
     */
    public void setPersonMap(Map<String, Object> personMap) {
        this.personMap = personMap;

        // Extraemos los datos del mapa con .get() y los convertimos a String
        firstNameField.setText((String) personMap.get("firstName"));
        lastNameField.setText((String) personMap.get("lastName"));
        streetField.setText((String) personMap.get("street"));
        cityField.setText((String) personMap.get("city"));

        // Cuidado con los enteros
        Object postalCode = personMap.get("postalCode");
        postalCodeField.setText(postalCode != null ? postalCode.toString() : "");

        // Cuidado con la fecha
        Object birthday = personMap.get("birthday");
        if (birthday instanceof LocalDate) {
            birthdayField.setText(DateUtil.format((LocalDate) birthday));
        } else {
            birthdayField.setText("");
        }

        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            // CAMBIO 3: Guardamos los datos de vuelta en el Mapa con .put()
            personMap.put("firstName", firstNameField.getText());
            personMap.put("lastName", lastNameField.getText());
            personMap.put("street", streetField.getText());
            personMap.put("city", cityField.getText());

            // Convertimos a entero
            personMap.put("postalCode", Integer.parseInt(postalCodeField.getText()));

            // Convertimos a LocalDate usando DateUtil
            personMap.put("birthday", DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * La validación es casi igual, solo verifica que los campos de texto no estén vacíos.
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}