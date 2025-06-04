package com.learning.book_management.service;

import com.learning.book_management.model.entity.Author;
import com.learning.book_management.model.entity.Book;
import com.learning.book_management.repository.AuthorRepository;
import com.learning.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    public Author createAuthorWithBooks(String authorName, List<String> bookTitles) {
        Author author = new Author();
        author.setName(authorName);

        List<Book> books = bookTitles.stream()
                .map(title -> {
                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    return book;
                })
                .toList();

        author.setBooks(books);
        return authorRepo.save(author);
    }
}
