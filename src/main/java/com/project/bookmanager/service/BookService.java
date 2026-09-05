package com.project.bookmanager.service;

import com.project.bookmanager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
        Page<Book> getAllBooks(int page, int pageSize,String sortBy, String sortOrder);
        Book getBookById(Long id);
        Book saveBook(Book book);

        Book updateBook(Long id, Book bookDetails);
        void deleteBook(Long id);
}
