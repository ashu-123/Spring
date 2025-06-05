package com.learning.book_management.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void testCreateAuthor() {
        String authorName = "Ashutosh Mishra";
        List<String> bookTitles = List.of("Effective Java", "Java concurrency in Action");
        var author = authorService.createAuthorWithBooks(authorName, bookTitles);
        assertEquals(2, author.getBooks().size());
    }
}