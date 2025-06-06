package com.learning.book_management.repository;

import com.learning.book_management.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Book book WHERE book.author.name = :authorName")
    void deleteAuthorWithName(String authorName);
}
