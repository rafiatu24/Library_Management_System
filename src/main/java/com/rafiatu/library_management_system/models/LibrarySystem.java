package com.rafiatu.library_management_system.models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// LibrarySystem class is now a final class to prevent subclassing
final class LibrarySystem {

    // Private constructor to prevent instantiation of the outer class
    private LibrarySystem() {}

    public static void main(String[] args) {
        // Instantiate the inner class LibrarySystemInner to manage resources
        LibrarySystemInner library = new LibrarySystemInner();

        // Create different types of library resources (Book and Magazine)
        LibraryResource book = new LibraryResource(1, "Effective Java");
        LibraryResource magazine = new Magazine(2, "Tech Monthly", "March 2024");

        // Add resources to the library
        library.addResource(book);
        library.addResource(magazine);

        // Display information for each resource in the library
        for (LibraryResource resource : library.getResourceList()) {
            library.displayResourceInfo(resource);
        }
    }

    // Inner class to manage the library system
    static class LibrarySystemInner {
        private LinkedList<LibraryResource> resourceList = new LinkedList<>();
        private LinkedList<Patron> patronList = new LinkedList<>();
        private Stack<Transaction> transactionHistory = new Stack<>();
        private Queue<Transaction> reservationQueue = new LinkedList<>();

        // Method to add resources to the library system
        public void addResource(LibraryResource resource) {
            this.resourceList.add(resource);
        }

        // Method to retrieve the list of resources
        public LinkedList<LibraryResource> getResourceList() {
            return this.resourceList;
        }

        // Method to display resource information
        public void displayResourceInfo(LibraryResource resource) {
            resource.displayInfo();
            System.out.println("--------------------");
        }
    }
}
