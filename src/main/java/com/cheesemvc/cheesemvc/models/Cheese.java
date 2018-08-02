package com.cheesemvc.cheesemvc.models;

public class Cheese {
    public String name;
    public String description;
    public int id;
    public static int nextId = 1;

    public Cheese(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Cheese() {
        this.id = nextId++;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
