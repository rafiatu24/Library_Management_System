package com.rafiatu.library_management_system.models;

public class Magazine extends LibraryResource {
    private String issueNumber;

    public Magazine(int resourceId, String title, String issueNumber) {
        super(resourceId, title);
        this.issueNumber = issueNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Issue Number: " + issueNumber);
    }

    public void setPublisher(String publisher) {
    }

    public void setIssueNumber(String issueNumber) {
    }
}
