package com.softuni.springintroex;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Engine implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public Engine(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthor();
        this.categoryService.seedCategory();
        this.bookService.seedBook();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       // this.bookService.printAllBooksByAgeRestriction(reader.readLine());
        //this.bookService.printAllBooksByEditionTypeAndCopiesLessThan500();
        //this.bookService.printAllBooksByPrice();
       // this.bookService.printAllBookByRealiseDateNotInYear(reader.readLine());
       // this.bookService.printAllBooksBeforeDate(reader.readLine());
        //this.authorService.printAllAuthorsFirstNameEndsWithGivenString(reader.readLine());
       // this.bookService.printAllBooksAuthorStartingWithGivenString(reader.readLine());
       // this.bookService.printCountOfBooks(Integer.parseInt(reader.readLine()));
        this.authorService.printAllAuthorsByBookCopies();
    }
}
