package com.example.itticketsystem;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import com.example.itticketsystem.model.Ticket;

public class AddTicket_Controller {

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<String> typeComboBox;    // ComboBox for ticket type
    @FXML private TextField dateField;

    // Initialize the ComboBox with predefined ticket types
    @FXML public void initialize() {
        typeComboBox.getItems().addAll("Critical Issue", "System Failure", "New Equipment", "Software Bug", "General Query");
    }

    // Handle the "Submit" action
    @FXML private void handleSubmit() {
        // Get the values from the TextField and ComboBox
        String ticketName = nameField.getText();
        String ticketType = typeComboBox.getValue();
        String ticketDescription = descriptionField.getText();
        String ticketDate = dateField.getText();

        boolean hasError = false;

        if (ticketName.isBlank()) {
            nameField.setPromptText("Please enter a name");
            hasError = true;
        }
        if (ticketType == null) {
            typeComboBox.setPromptText("Please select a type");
            hasError = true;
        }
        if (ticketDescription.isBlank()) {
            descriptionField.setPromptText("Please enter a description");
            hasError = true;
        }
        if (ticketDate.isBlank()) {
            dateField.setPromptText("Please enter a date");
            hasError = true;
        }

        if (hasError) {
            return; // Stop submission if any field is empty
        }

        // Get priority based on the selected type
        int ticketPriority = new Ticket(0, true, ticketType, ticketDescription, ticketName, ticketDate).getTypePriority();

        // Create a new Ticket with the selected type and set the priority
        Ticket newTicket = new Ticket(ticketPriority, true, ticketType, ticketDescription, ticketName, ticketDate);

        // Print ticket details for testing
        System.out.println("Ticket Submitted");
        System.out.println("Name: " + newTicket.getName());
        System.out.println("Type: " + newTicket.getType());
        System.out.println("Description: " + newTicket.getDescription());
        System.out.println("Priority: " + newTicket.getPriority());
        System.out.println("Date: " + newTicket.getDate());

        // Close the window after submitting the ticket
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }


    // Handle the "Cancel" action (closes the window)
    @FXML private void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
