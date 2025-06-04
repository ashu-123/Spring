package com.learning.book_management.model.entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Long getId() {
        return id;
    }

    public Review setId(Long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Review setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Review setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Review setBook(Book book) {
        this.book = book;
        return this;
    }
}

