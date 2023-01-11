package com.example.sweater.repos;

import com.example.sweater.domain.TestBulkInsert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBulkInsertRepository extends JpaRepository<TestBulkInsert, Long> {
}
