package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthor() throws IOException {
        if (this.authorRepository.count() !=0){
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

        for (String s : fileContent) {
            String[] tokens = s.split("\\s+");
            Author author = new Author(tokens[0], tokens[1]);
            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(long randomId) {
        return this.authorRepository.getOne(randomId);
    }

    @Override
    public void printAllAuthorsFirstNameEndsWithGivenString(String word) {
        this.authorRepository.findAllByFirstNameEndingWith(word)
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    @Override
    public void printAllAuthorsByBookCopies() {
        List<Author> authors = this.authorRepository.findAll();
        Map<String, Integer> authorCopies = new HashMap<>();

        authors.forEach(author -> {
            int copies = author.getBooks().stream().mapToInt(Book::getCopies).sum();
            authorCopies.put(author.getFirstName() + " " + author.getLastName(), copies);
        });

        authorCopies
                .entrySet()
                .stream()
                .sorted((current, next) -> Integer.compare(next.getValue(), current.getValue()))
                .forEach(author -> System.out.printf("%s %d%n", author.getKey(), author.getValue()));
    }
}
