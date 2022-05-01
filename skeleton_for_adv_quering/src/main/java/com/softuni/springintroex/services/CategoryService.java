package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategory() throws IOException;

    Category getCategoryById(long categoryId);
}
