package com.project.bookmanager.service.impl;

import com.project.bookmanager.dto.BookAddDto;
import com.project.bookmanager.dto.BookUpdateDto;
import com.project.bookmanager.entity.Book;
import com.project.bookmanager.exceptions.BookAlreadyExistsException;
import com.project.bookmanager.exceptions.ResourceNotFoundException;
import com.project.bookmanager.repo.BookRepository;
import com.project.bookmanager.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getAllBooks(int  page, int pageSize, String sortBy, String sortOrder)
    {       Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
            ?Sort.by(sortBy).ascending()
            :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,pageSize,sort);

        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id));
    }

    @Override
    public Book saveBook(BookAddDto bookDto) {
        if(!bookRepository.existsByisbn(bookDto.getIsbn())){
            Book book = new Book();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setPrice(bookDto.getPrice());
            book.setCategory(bookDto.getCategory());
            book.setIsbn(bookDto.getIsbn());
            book.setDescription(bookDto.getDescription());
            book.setCoverImageURL(bookDto.getCoverImageURL());
            book.setStockQuantity(bookDto.getStockQuantity());
            return  bookRepository.save(book);
        }
        else throw new BookAlreadyExistsException("Book Already Exists with the isbn: " + bookDto.getIsbn());
    }

    @Override
    public Book updateBook(Long id, BookUpdateDto bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id));

        if (bookDetails.getTitle() != null) {
            existingBook.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getAuthor() != null) {
            existingBook.setAuthor(bookDetails.getAuthor());
        }
        if (bookDetails.getPrice() != null) {
            existingBook.setPrice(bookDetails.getPrice());
        }
        if (bookDetails.getStockQuantity() != null) {
            existingBook.setStockQuantity(bookDetails.getStockQuantity());
        }
        if (bookDetails.getDescription() != null) {
            existingBook.setDescription(bookDetails.getDescription());
        }
        if (bookDetails.getCategory() != null) {
            existingBook.setCategory(bookDetails.getCategory());
        }
        if (bookDetails.getCoverImageURL() != null) {
            existingBook.setCoverImageURL(bookDetails.getCoverImageURL());
        }
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id).

                orElseThrow(()-> new ResourceNotFoundException("Book Not Found with id: " + id)) ;
        bookRepository.deleteById(id);
    }
}
