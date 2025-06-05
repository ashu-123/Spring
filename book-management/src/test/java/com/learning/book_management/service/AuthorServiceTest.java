package com.learning.book_management.service;

import com.learning.book_management.model.entity.Book;
import com.learning.book_management.repository.AuthorRepository;
import com.learning.book_management.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testCreateAuthor() {
        String authorName = "Ashutosh Mishra";
        List<String> bookTitles = new ArrayList<>();
        bookTitles.add("Effective Java");
        bookTitles.add("Java concurrency in practice");
        var author = authorService.createAuthorWithBooks(authorName, bookTitles);
        assertEquals(2, author.getBooks().size());
        assertEquals(2L, bookRepository.count());

        Book removedBook = null;
        for(Book book : author.getBooks()) {
            if(book.getTitle().equals("Effective Java")) {
                removedBook = book;
                break;
            }
        }
        System.out.println(removedBook);
        removedBook.setAuthor(null);
        author.getBooks().remove(removedBook);
        authorRepository.save(author);
        assertEquals(1, author.getBooks().size());
        assertEquals(1L, bookRepository.count());
    }
}