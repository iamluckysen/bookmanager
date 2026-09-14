package com.project.bookmanager.repo;

import com.project.bookmanager.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
 List<BorrowRecord> findByUserId(Long userId);
}
