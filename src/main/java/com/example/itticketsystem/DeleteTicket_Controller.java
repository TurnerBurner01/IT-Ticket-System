package com.example.itticketsystem;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteTicket_Controller {

    private Dashboard_Controller dashboardController;  // Reference to parent controller
    @FXML private TextField deleteIdField;             // Reference to ID text field
    @FXML private CheckBox deleteCheckbox;             // Reference to checkbox

    public boolean checkBoxSelected = false;

    // Set the Dashboard_Controller reference
    public void setDashboardController(Dashboard_Controller dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML private void handleSubmit() {
        String id = deleteIdField.getText();

        if (deleteCheckbox.isSelected()) {
            checkBoxSelected = true;
            System.out.println("Checkbox selected");
        }

        // Close the window after deleting the ticket
        Stage stage = (Stage) deleteIdField.getScene().getWindow();
        stage.close();
    }

    @FXML private void handleCancel() {
        // Close the window
        Stage stage = (Stage) deleteIdField.getScene().getWindow();
        stage.close();
    }

}
