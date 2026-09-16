package com.project.bookmanager.service;

import com.project.bookmanager.entity.BorrowRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface BorrowRecordService {
    @Transactional
    public BorrowRecord borrowBook(Long bookId, String userEmail);

    @Transactional
    public BorrowRecord returnBook(Long bookId, String userEmail);

}
