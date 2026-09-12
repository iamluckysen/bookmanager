package com.project.bookmanager.controller;


import com.project.bookmanager.dto.BookAddDto;
import com.project.bookmanager.dto.BookUpdateDto;
import com.project.bookmanager.entity.Book;
import com.project.bookmanager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)  int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder
    )
    {
        return ResponseEntity.ok(bookService.getAllBooks(pageNo,pageSize,sortBy,sortOrder));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookAddDto book)
    {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook( @Valid @PathVariable Long id, @RequestBody BookUpdateDto bookDetails){
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted succesfully");
    }


}
