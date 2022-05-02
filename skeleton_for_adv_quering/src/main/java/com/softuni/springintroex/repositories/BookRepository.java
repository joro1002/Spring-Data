package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal greaterBound);

    @Query("SELECT b FROM Book b WHERE SUBSTRING(b.releaseDate, 0, 4) not like :year")
    Set<Book> findAllByReleaseDateNotInYear(String year);

    Set<Book>findAllByReleaseDateIsLessThan(LocalDate date);

    Set<Book> findAllByAuthorLastNameStartingWith(String word);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :length")
    int getAllBooksWithTitleIsLongerThanGivenNumber(int length);
}
