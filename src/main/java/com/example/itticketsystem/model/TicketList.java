package com.example.itticketsystem.model;

public class TicketList {
    private Ticket[] tickets;
    private int size;

    public TicketList() {
        tickets = new Ticket[10]; // Initial capacity, can be adjusted
        size = 0;
    }

    // Add a ticket to the list
    public void add(Ticket ticket) {
        if (size >= tickets.length) {
            // Resize array if needed
            Ticket[] newTickets = new Ticket[tickets.length * 2];
            System.arraycopy(tickets, 0, newTickets, 0, tickets.length);
            tickets = newTickets;
        }
        tickets[size++] = ticket;
    }

    // Get all tickets in the list
    public Ticket[] getTickets() {
        Ticket[] result = new Ticket[size];
        System.arraycopy(tickets, 0, result, 0, size);
        return result;
    }

    // Get the size of the list
    public int size() {
        return size;
    }
}
