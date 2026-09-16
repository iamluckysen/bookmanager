package com.project.bookmanager.controller;

import com.project.bookmanager.service.BorrowRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private BorrowRecordService borrowRecordService;

    public BorrowController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<String> borrow(@PathVariable Long bookId, Principal principal) {
        String userEmail = principal.getName();

        borrowRecordService.borrowBook(bookId, userEmail);
        return ResponseEntity.ok().body("book added to your account");
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, Principal principal) {
        String userEmail = principal.getName();
        borrowRecordService.returnBook(bookId, userEmail);
        return ResponseEntity.ok().body("book returned to your account");
    }
}
