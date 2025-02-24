package com.example.itticketsystem.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ticket {
    private int priority;
    private int id;
    private Boolean status;
    private String type;
    private String description;
    private String name;
    private String date;

    private static final Random random = new Random();

    // Constructor to create a ticket
    public Ticket(int priority, int id, Boolean status, String type, String description, String name, String date) {
        this.priority = priority;
        this.id = generateID();
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

    // Generate Random ID for each ticket
    public int generateID() {
        return 100 + random.nextInt(990);
    }

    // Getter and Setter methods
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
