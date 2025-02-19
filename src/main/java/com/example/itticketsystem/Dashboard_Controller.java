package com.example.itticketsystem;

import com.example.itticketsystem.model.Ticket;
import com.example.itticketsystem.data_structure.BinarySearchTree;
import com.example.itticketsystem.model.TicketList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Dashboard_Controller {

    // Initialise Table Columns
    @FXML private TableView<Ticket> ticketTable;
    @FXML private TableColumn<Ticket, String> priorityColumn;
    @FXML private TableColumn<Ticket, Boolean> statusColumn;
    @FXML private TableColumn<Ticket, String> typeColumn;
    @FXML private TableColumn<Ticket, String> descriptionColumn;
    @FXML private TableColumn<Ticket, String> nameColumn;
    @FXML private TableColumn<Ticket, String> dateColumn;

    private BinarySearchTree ticketService;
    private TicketList ticketList;

    @FXML public void initialize() {
        // Initialize the BST instead of TicketService
        ticketService = new BinarySearchTree();
        ticketList = new TicketList();

        // Set up table columns
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadTestData();
    }

    private void loadTestData() {
        // Add tickets to the BST using the insert method
        ticketService.insert(new Ticket(0, false, "Set up computer", "I need my laptop set up", "Joshua T", "19/02/2025"));
        ticketService.insert(new Ticket(0, false, "New Equipment", "I need a new USB stick", "James Sim", "20/02/2025"));
        ticketService.insert(new Ticket(0, false, "Server Crash", "The Server has crashed", "Dan Potter", "5/01/2025"));

        // Update priorities based on the BST position
        ticketService.updatePriorities();

        // Add the sorted tickets to the custom list
        Ticket[] sortedTickets = ticketService.getAllTickets();
        for (Ticket ticket : sortedTickets) {
            ticketList.add(ticket);
        }

        // Bind the custom list to the TableView (Note: You'll need to manually populate the TableView)
        ticketTable.getItems().clear();
        for (Ticket ticket : ticketList.getTickets()) {
            ticketTable.getItems().add(ticket);
        }
    }
}
