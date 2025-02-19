package com.example.itticketsystem.model;

/**
 * This class is used to alter tickets:
 * - Add Tickets
 * - Delete Tickets
 * - Search Tickets
 * - Update Priority
 */

public class TicketService {
    private Ticket[] tickets;
    private int size;

    public TicketService(int capacity) {
        this.tickets = new Ticket[capacity];
        this.size = 0;
    }

    public void addTicket(Ticket ticket) {
        // Checks if storage is full
        if (size < tickets.length) {
            tickets[size] = ticket;
            size++;
        } else {
            System.out.println("Ticket storage is full.");
        }
    }

    public Ticket[] getAllTickets() {
        Ticket[] result = new Ticket[size];
        System.arraycopy(tickets, 0, result, 0, size);
        return result;
    }
}

