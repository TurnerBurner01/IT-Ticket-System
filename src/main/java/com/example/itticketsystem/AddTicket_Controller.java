package com.example.itticketsystem;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.itticketsystem.model.Ticket;

public class AddTicket_Controller {

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<String> typeComboBox;    // ComboBox for ticket type
    @FXML private TextField dateField;

    private Dashboard_Controller dashboardController;  // Reference to parent controller

    // Initialize the ComboBox with predefined ticket types
    @FXML public void initialize() {
        typeComboBox.getItems().addAll("Critical Issue", "System Failure", "Software Bug", "New Equipment", "General Query");
    }

    // Set the Dashboard_Controller reference
    public void setDashboardController(Dashboard_Controller dashboardController) {
        this.dashboardController = dashboardController;
    }

    // Handle the "Submit" action
    @FXML private void handleSubmit() {
        // Get the values from the TextField and ComboBox
        String ticketName = nameField.getText();
        String ticketType = typeComboBox.getValue();
        String ticketDescription = descriptionField.getText();
        String ticketDate = dateField.getText();

        // Checks to see if there are any empty fields
        if (ticketName.isEmpty() || ticketType == null || ticketDescription.isEmpty() || ticketDate.isEmpty()) {
            // Highlight missing fields
            if (ticketName.isEmpty()) {
                nameField.setPromptText("ENTER A NAME");
            }
            if (ticketType == null) {
                typeComboBox.setPromptText("ENTER A TYPE");
            }
            if (ticketDescription.isEmpty()) {
                descriptionField.setPromptText("ENTER A DESCRIPTION");
            }
            if (ticketDate.isEmpty()) {
                dateField.setPromptText("ENTER A DATE");
            }
            return;
        }

        // Get priority based on the selected type
        int ticketPriority = new Ticket(0, 0, true, ticketType, ticketDescription, ticketName, ticketDate).getTypePriority();

        // Create a new Ticket with the selected type and set the priority
        Ticket newTicket = new Ticket(ticketPriority, 0, true, ticketType, ticketDescription, ticketName, ticketDate);

        // Pass the new ticket to the Dashboard_Controller
        if (dashboardController != null) {
            dashboardController.addTicketToTable(newTicket);  // Add ticket to parent controller's table
        }

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
