package com.example.itticketsystem;

import com.example.itticketsystem.model.Ticket;
import com.example.itticketsystem.model.TicketService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private TicketService ticketService;
    private ObservableList<Ticket> ticketList;

    @FXML public void initialize() {
        ticketService = new TicketService(10);
        ticketList = FXCollections.observableArrayList();

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
        ticketService.addTicket(new Ticket(1, true,"Set up computer", "I need my laptop set up", "Joshua T", "19/02/2025"));
        ticketService.addTicket(new Ticket(2, false, "New Equipment", "I need a new USB stick", "James Sim", "20/02/2025"));

        ticketList.addAll(ticketService.getAllTickets());
        ticketTable.setItems(ticketList);
    }
}
