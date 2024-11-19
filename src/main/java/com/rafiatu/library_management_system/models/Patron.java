package com.rafiatu.library_management_system.models;

public class Patron {
    private int patronId;
    private String name;
    private String contact;

    public Patron(int patronId, String name, String contact) {
        this.patronId = patronId;
        this.name = name;
        this.contact = contact;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
