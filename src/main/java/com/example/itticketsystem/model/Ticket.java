package com.example.itticketsystem.model;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private int priority;
    private Boolean status;
    private String type;
    private String description;
    private String name;
    private String date;

    // Constructor to create a ticket
    public Ticket(int priority,Boolean status, String type, String description, String name, String date) {
        this.priority = priority;
        this.status = status;
        this.type = type;
        this.description = description;
        this.name = name;
        this.date = date;
    }



    // Create priority for types of issues
    public int getTypePriority() {
        Map<String, Integer> typePriorityMap = new HashMap<>();
        typePriorityMap.put("Critical Issue", 1);
        typePriorityMap.put("System Failure", 2);
        typePriorityMap.put("Software Bug", 3);
        typePriorityMap.put("New Equipment", 4);
        typePriorityMap.put("General Query", 5);

        return typePriorityMap.getOrDefault(type, 10); // Default to lowest priority if type is unknown
    }

    // Gets and Sets Methods

    // -------> Priority

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // -------> Status
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
