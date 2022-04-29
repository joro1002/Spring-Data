package com.softuni.springdataintro.services;

import com.softuni.springdataintro.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBook() throws IOException;

    List<Book> getAllBooksAfter2000();
}
