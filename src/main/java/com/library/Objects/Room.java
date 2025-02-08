package com.library.Objects;

public class Room {

    private int id;
    private int number;
    private String name;
    private int capacity;
    private String address;

    public Room() {
    }

    public Room(int id, int number, String name, int capacity, String address) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public Room(int number, String name, int capacity, String address) {
        this.number = number;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
