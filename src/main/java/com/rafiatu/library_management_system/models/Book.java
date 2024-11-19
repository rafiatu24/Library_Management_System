package com.rafiatu.library_management_system.models;

//import jdk.internal.icu.text.UTF16;

import java.time.LocalDate;

public class Book {

//    public static UTF16 Genre;
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(int bookId, String title, String genre, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.available = available;
    }

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPublicationDate(LocalDate publicationDate) {
    }

    public void setResourceId(int resourceId) {
    }

    public void setIsbn(String isbn) {
    }


    // Subclass for books
    public static class book extends LibraryResource {
        private String author;

        public book(int resourceId, String title, String author) {
            super(resourceId, title);
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Author: " + author);
        }
    }
}


