package com.example.itticketsystem.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.itticketsystem.data_structure.NewHashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Ticket {
    private int priority;
    private int id;
    private Boolean status;
    private String type;
    private String description;
    private String name;
    private String date;

    private static final Random random = new Random();
    private BooleanProperty selected;  // Changed this to BooleanProperty

    // Constructor to create a ticket
    public Ticket(int priority, int id, Boolean status, String type, String description, String name, String date) {
        this.priority = priority;
        this.id = generateID();
        this.status = status;
        this.type = type;
        this.description = description;
        this.name = name;
        this.date = date;
        this.selected = new SimpleBooleanProperty(false);  // Initialize the selected property
    }

    // Create priority for types of issues
    public int getTypePriority() {
        NewHashMap typePriorityMap = new NewHashMap();
        typePriorityMap.put("Critical Issue", 1);
        typePriorityMap.put("System Failure", 2);
        typePriorityMap.put("Software Bug", 3);
        typePriorityMap.put("New Equipment", 4);
        typePriorityMap.put("General Query", 5);

        return typePriorityMap.getOrDefault(type, 10); // Default to lowest priority if type is unknown
    }

    // Generate Random ID for each ticket
    public int generateID() {
        return random.nextInt(999);
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

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }
}
