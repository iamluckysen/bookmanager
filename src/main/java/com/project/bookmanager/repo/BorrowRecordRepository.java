package com.project.bookmanager.repo;

import com.project.bookmanager.entity.Book;
import com.project.bookmanager.entity.BorrowRecord;
import com.project.bookmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
 List<BorrowRecord> findByUserId(Long userId);
 Optional<BorrowRecord> findByUserIdAndBookIdAndStatus(Long userId, Long bookId,String status );
}
