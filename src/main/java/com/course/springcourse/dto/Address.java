package com.course.springcourse.dto;

public class Address {
    private int id;
    private String address;

    public Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
