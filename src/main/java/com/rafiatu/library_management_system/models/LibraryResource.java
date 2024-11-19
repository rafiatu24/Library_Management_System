package com.rafiatu.library_management_system.models;

// Base class
public class LibraryResource {
    private int resourceId;
    private String title;

    public LibraryResource(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }


    public int getResourceId() {

        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void displayInfo() {
        System.out.println("Resource ID: " + resourceId);
        System.out.println("Title: " + title);
    }

//    public String getResourceStatus() {
//    }
//
//    public void setResourceId(int id) {
//    }
//
//    public Object getResourceType() {
//    }
//
//    public void setTitle(String title) {
//    }
}

