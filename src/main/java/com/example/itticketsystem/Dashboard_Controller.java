package com.example.itticketsystem;

import com.example.itticketsystem.model.Ticket;
import com.example.itticketsystem.data_structure.BinarySearchTree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dashboard_Controller {

    // Initialise Table Columns
    @FXML private TableView<Ticket> ticketTable;
    @FXML private TableColumn<Ticket, String> priorityColumn;
    @FXML private TableColumn<Ticket, String> idColumn;
    @FXML private TableColumn<Ticket, Boolean> statusColumn;
    @FXML private TableColumn<Ticket, String> typeColumn;
    @FXML private TableColumn<Ticket, String> descriptionColumn;
    @FXML private TableColumn<Ticket, String> nameColumn;
    @FXML private TableColumn<Ticket, String> dateColumn;

    // Initialise ComboBoxes
    @FXML private ComboBox<String> columnComboBox;
    @FXML private ComboBox<String> detailsComboBox;

    private BinarySearchTree ticketService;

    @FXML public void initialize() {
        // Initialize Binary Search Tree
        ticketService = new BinarySearchTree();

        // Set up table columns
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(column -> new javafx.scene.control.cell.TextFieldTableCell<Ticket, Boolean>() {
            // Method makes true : false = Active : Solved
            @Override
            public void updateItem(Boolean status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle(""); // Reset style if the cell is empty
                } else {
                    setText(status ? "Active" : "Solved"); // Set the status text
                    if (status) {
                        setStyle("-fx-background-color: #FA5053; -fx-text-fill: white;");
                    } else {
                        setStyle("-fx-background-color: #3CAE63; -fx-text-fill: white;");
                    }
                }
            }
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Add test data
        loadTestData();

        // Populate ComboBox 1
        columnComboBox.getItems().addAll("Priority", "ID", "Status", "Type", "Name", "Date");
        // Add a listener to the first ComboBox
        columnComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDetailsComboBox(newValue);
        });
    }

    private void loadTestData() {
        // Adding test data to BinarySearchTree
        ticketService.insert(new Ticket(0, 0, true, "Critical Issue", "Floor Shut Down", "James W", "6/03/2025"));
        ticketService.insert(new Ticket(0,0, true, "System Failure", "Urgent fix needed", "Alice W", "18/02/2025"));
        ticketService.insert(new Ticket(0,0, false, "New Equipment", "Need a new monitor", "Bob J", "19/02/2025"));
        ticketService.insert(new Ticket(0,0, true, "Critical Issue", "Server down", "Charlie Z", "17/02/2025"));
        ticketService.insert(new Ticket(0,0,  false, "Software Bug", "Photoshop not working", "David L", "20/02/2025"));

        // Updating priorities
        ticketService.updatePriorities();

        // Adding all tickets to TableView
        ticketTable.getItems().addAll(ticketService.getAllTickets());
    }

    // Opens the AddTicket Window
    @FXML private void openAddTicketWindow() {
        try {
            // Load the "Add Ticket" window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-ticket.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller of the AddTicket window
            AddTicket_Controller addTicketController = fxmlLoader.getController();

            // Pass the reference of this (Dashboard_Controller) to the AddTicket_Controller
            addTicketController.setDashboardController(this);

            // Show the AddTicket window
            Stage stage = new Stage();
            stage.setTitle("Add New Ticket");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add ticket to the table from AddTicket_Controller
    public void addTicketToTable(Ticket newTicket) {
        ticketService.insert(newTicket);                                // Add ticket to BinarySearchTree
        ticketService.updatePriorities();                               // Update priorities after insertion
        ticketTable.getItems().clear();                                 // Clear the TableView before reloading
        ticketTable.getItems().addAll(ticketService.getAllTickets());   // Add updated tickets to TableView
    }

    // Method is used to reset / repopulate the table to its original state
    @FXML private void refreshTable() {
        // Reset ComboBoxes
        columnComboBox.setValue(null);
        detailsComboBox.setValue(null);

        // Gather all tickets from the BST
        Ticket[] allTickets = ticketService.getAllTickets();

        // Clear the table
        ticketTable.getItems().clear();

        // Add tickets to the table
        for (Ticket ticket : allTickets) {
            ticketTable.getItems().add(ticket);
        }
    }

    // Method is used to search the BST and alter the table content based on attributes elected
    @FXML private void searchTickets() {
        // Get the selected column and filter value
        String selectedColumn = columnComboBox.getValue();
        String selectedValue = detailsComboBox.getValue();

        // Checks if the ComboBoxes are not empty -> If so then nothing happens
        if (selectedColumn != null && selectedValue != null) {
            // Filter tickets based on the selected column and value
            Ticket[] filteredTickets = filterTickets(selectedColumn, selectedValue);

            // Update the table with the filtered tickets
            ticketTable.getItems().clear();
            ticketTable.getItems().addAll(filteredTickets);
        }
    }

    // Method is used to filter through tickets in BST to find the correct tickets
    private Ticket[] filterTickets(String selectedColumn, String selectedValue) {
        // Get all tickets from the BinarySearchTree
        Ticket[] allTickets = ticketService.getAllTickets();

        // Temporary array for storing filtered tickets (size set to number of tickets)
        Ticket[] tempFiltered = new Ticket[allTickets.length];
        int count = 0;

        // Iterate through all tickets
        for (Ticket ticket : allTickets) {
            // Iterates through the selected column of the table using switch case
            boolean matches = switch (selectedColumn) {
                case "Priority" -> String.valueOf(ticket.getPriority()).equals(selectedValue);
                case "ID" -> String.valueOf(ticket.getId()).equals(selectedValue);
                case "Status" -> (ticket.getStatus() ? "Active" : "Solved").equals(selectedValue);
                case "Type" -> ticket.getType().equals(selectedValue);
                case "Name" -> ticket.getName().equals(selectedValue);
                case "Date" -> ticket.getDate().equals(selectedValue);
                default -> false;
            };

            // If the ticket matches the filter, add it to the temporary array
            if (matches) {
                tempFiltered[count] = ticket;
                count++;
            }
        }

        // Create an array to return
        Ticket[] filteredArray = new Ticket[count];
        for (int i = 0; i < count; i++) {
            filteredArray[i] = tempFiltered[i];
        }

        return filteredArray;
    }


    // Method to populate the detailsComboBox
    public void updateDetailsComboBox(String selectedField) {
        detailsComboBox.getItems().clear(); // Clear existing items in the detailsComboBox

        if (selectedField != null) {
            // Get all tickets from the table
            Ticket[] tickets = ticketTable.getItems().toArray(new Ticket[0]);

            // Temporary array to store unique values (size set to number of tickets)
            String[] uniqueValues = new String[tickets.length];
            int uniqueCount = 0;

            for (Ticket ticket : tickets) {
                String value = switch (selectedField) {
                    case "Priority" -> String.valueOf(ticket.getPriority());
                    case "ID" -> String.valueOf(ticket.getId());
                    case "Status" -> ticket.getStatus() ? "Active" : "Solved";
                    case "Type" -> ticket.getType();
                    case "Name" -> ticket.getName();
                    case "Date" -> ticket.getDate();
                    default -> throw new IllegalStateException("Unexpected value: " + selectedField);
                };

                // Check if value is already in the uniqueValues array
                boolean isDuplicate = false;
                for (int i = 0; i < uniqueCount; i++) {
                    if (uniqueValues[i].equals(value)) {
                        isDuplicate = true;
                        break;
                    }
                }

                // If not a duplicate, add to the array
                if (!isDuplicate) {
                    uniqueValues[uniqueCount] = value;
                    uniqueCount++;
                }
            }

            // Add unique values to the ComboBox
            for (int i = 0; i < uniqueCount; i++) {
                detailsComboBox.getItems().add(uniqueValues[i]);
            }
        }
    }

}

