package com.project.bookmanager.service.impl;

import com.project.bookmanager.entity.Book;
import com.project.bookmanager.entity.BorrowRecord;
import com.project.bookmanager.entity.User;
import com.project.bookmanager.exceptions.ResourceNotFoundException;
import com.project.bookmanager.repo.BookRepository;
import com.project.bookmanager.repo.BorrowRecordRepository;
import com.project.bookmanager.repo.UserRepository;
import com.project.bookmanager.service.BorrowRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    private BorrowRecordRepository borrowRecordRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BorrowRecordServiceImpl(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public BorrowRecord borrowBook(Long bookId, String userEmail) {
        Book book  = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(book.getStockQuantity()<=0){
            throw new RuntimeException("book is out of stock");
        }

        book.setStockQuantity(book.getStockQuantity()-1);
        bookRepository.save(book);

        BorrowRecord borrowRecord = new BorrowRecord();

        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setStatus("BORROWED");

        return borrowRecordRepository.save(borrowRecord);
    }
}
