package com.softuni.springdataintro.services.impl;

import com.softuni.springdataintro.constants.GlobalConstants;
import com.softuni.springdataintro.entities.Author;
import com.softuni.springdataintro.repositories.BookRepository;
import com.softuni.springdataintro.services.AuthorService;
import com.softuni.springdataintro.services.BookService;
import com.softuni.springdataintro.utils.FileUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;

    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
    }

    @Override
    public void seedBook() throws IOException {
        if (this.bookRepository.count() != 0){
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] tokens = r.split("\\s+");

                    Author author = this.getRandomAuthor();
                    System.out.println();

                });
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);

    }
}
