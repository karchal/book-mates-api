package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;

public final class BookUtils {
    private BookUtils() {}

    public static boolean isDistinctByTitleAndAuthor(Book book, Book otherBook) {
        if (book == null || otherBook == null || book.getTitle() == null || otherBook.getTitle() == null || book.getAuthor() == null || otherBook.getAuthor() == null) {
            return false;
        }
        return !book.getAuthor().equalsIgnoreCase(otherBook.getAuthor()) && book.getTitle().equalsIgnoreCase(otherBook.getTitle());
    }
}
