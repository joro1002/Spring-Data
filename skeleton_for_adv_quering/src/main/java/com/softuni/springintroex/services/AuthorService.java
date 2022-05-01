package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthor() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(long randomId);
}
