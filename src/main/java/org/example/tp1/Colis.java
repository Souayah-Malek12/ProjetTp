package org.example.tp1;

public class Colis {
    private String id;
    private String destination;

    public Colis(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Destination: " + destination;
    }
}
