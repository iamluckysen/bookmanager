package com.project.bookmanager.service.impl;

import com.project.bookmanager.entity.Book;
import com.project.bookmanager.exceptions.ResourceNotFoundException;
import com.project.bookmanager.repo.BookRepository;
import com.project.bookmanager.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id));

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setCategory(bookDetails.getCategory());
        existingBook.setCoverImageURL(bookDetails.getCoverImageURL());
        existingBook.setStockQuantity(bookDetails.getStockQuantity());
        existingBook.setDescription(bookDetails.getDescription());
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id).

                orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id)) ;
        bookRepository.deleteById(id);
    }
}
