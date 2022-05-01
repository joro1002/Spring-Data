package com.softuni.springintroex.services;

import java.io.IOException;

public interface BookService {
    void seedBook() throws IOException;
    void printAllBooksByAgeRestriction(String ageRestriction);
    void printAllBooksByEditionTypeAndCopiesLessThan500();

    void printAllBooksByPrice();
}
