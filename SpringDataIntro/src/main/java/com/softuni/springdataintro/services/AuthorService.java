package com.softuni.springdataintro.services;

import com.softuni.springdataintro.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthor() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(Long id);
}
