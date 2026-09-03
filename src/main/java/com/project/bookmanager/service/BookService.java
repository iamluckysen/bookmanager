package com.project.bookmanager.service;

import com.project.bookmanager.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
        List<Book> getAllBooks();
        Book getBookById(Long id);
        Book saveBook(Book book);

        Book updateBook(Long id, Book bookDetails);
        void deleteBook(Long id);
}
