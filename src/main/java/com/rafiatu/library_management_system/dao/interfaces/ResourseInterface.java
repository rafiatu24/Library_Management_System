package com.rafiatu.library_management_system.dao.interfaces;

import com.rafiatu.library_management_system.models.Book;
import com.rafiatu.library_management_system.models.LibraryResource;
import com.rafiatu.library_management_system.models.Magazine;

import java.util.LinkedList;

public interface ResourseInterface {
    boolean addResourse(LibraryResource libraryResource);
    boolean deleteResourse(Integer resourseId);
    boolean updateResourse(LibraryResource libraryResource);
    boolean addMagazine(Magazine magazine);
    boolean addBook(Book book);
    LinkedList<Book> getAllBooks();
    LinkedList<Magazine> getAllMagazines();
//    LinkedList<Magazine> getMagazinesByAuthor(String author);
    LinkedList<LibraryResource> getAllLibraryResources();
    LibraryResource findResourseById(Integer resourseId);


}
