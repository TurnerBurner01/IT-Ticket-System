package com.example.itticketsystem.model;

public class Ticket {
    private int priority;
    private String type;
    private String description;
    private String name;
    private String date;

    // Constructor to create a ticket
    public Ticket(int priority, String type, String description, String name, String date) {
        this.priority = priority;
        this.type = type;
        this.description = description;
        this.name = name;
        this.date = date;
    }

    // Gets and Sets Methods

    // -------> Priority
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // -------> Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // -------> Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // -------> Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -------> Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
