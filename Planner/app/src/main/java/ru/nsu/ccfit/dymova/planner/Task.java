package ru.nsu.ccfit.dymova.planner;

public class Task {
    private String name;
    private String type;
    private String description;

    private long id = -1;

    public Task(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
