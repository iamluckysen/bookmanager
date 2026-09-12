package com.project.bookmanager.service;

import com.project.bookmanager.dto.BookAddDto;
import com.project.bookmanager.dto.BookUpdateDto;
import com.project.bookmanager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
        Page<Book> getAllBooks(int page, int pageSize,String sortBy, String sortOrder);
        Book getBookById(Long id);
        Book saveBook(BookAddDto book);

        Book updateBook(Long id, BookUpdateDto bookUpdateDto);
        void deleteBook(Long id);
}
