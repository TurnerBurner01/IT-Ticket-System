package com.example.itticketsystem;

import com.example.itticketsystem.model.Ticket;
import com.example.itticketsystem.data_structure.BinarySearchTree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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

    private BinarySearchTree ticketService;

    @FXML public void initialize() {
        // Initialize the BinarySearchTree service for tickets
        ticketService = new BinarySearchTree();

        // Set up table columns
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTestData();
    }

    private void loadTestData() {
        // Adding test data to BinarySearchTree
        ticketService.insert(new Ticket(0, 0, true, "Critical Issue", "Floor Shut Down", "James W", "6/03/2025"));
        ticketService.insert(new Ticket(0,0, true, "System Failure", "Urgent fix needed", "Alice W", "18/02/2025"));
        ticketService.insert(new Ticket(0,0, true, "New Equipment", "Need a new monitor", "Bob J", "19/02/2025"));
        ticketService.insert(new Ticket(0,0, true, "Critical Issue", "Server down", "Charlie Z", "17/02/2025"));
        ticketService.insert(new Ticket(0,0,  false, "Software Bug", "Photoshop not working", "David L", "20/02/2025"));

        // Updating priorities
        ticketService.updatePriorities();

        // Adding all tickets to TableView
        ticketTable.getItems().addAll(ticketService.getAllTickets());
    }

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

    @FXML private void openDeleteTicketWindow() {
        try {
            // Load the "Delete Ticket" window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-ticket.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller of the DeleteTicket window
            DeleteTicket_Controller DeleteTickerController = fxmlLoader.getController();

            // Pass the reference of this (Dashboard_Controller) to the DeleteTicket_Controller
            DeleteTickerController.setDashboardController(this);

            // Show the DeleteTicket window
            Stage stage = new Stage();
            stage.setTitle("Delete Ticket");
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
}
