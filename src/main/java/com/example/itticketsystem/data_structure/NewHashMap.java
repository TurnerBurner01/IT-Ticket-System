package com.example.itticketsystem.data_structure;

public class NewHashMap {
    private String[] keys;
    private int[] values;
    public int size;

    public NewHashMap() {
        keys = new String[10];
        values = new int[10];
        size = 0;
    }

    public void put(String key, int value) {
        for (int i = 0; i < size; i++) {
            if (key != null && key.equals(keys[i])) {
                values[i] = value; // Update
                return;
            }
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    public int getOrDefault(String key, int defaultValue) {
        for (int i = 0; i < size; i++) {
            if (key != null && key.equals(keys[i])) {
                return values[i];
            }
        }
        return defaultValue;
    }
}
