package com.example.sweater.repos;

import com.example.sweater.domain.TableWithClob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableWithClobRepository extends JpaRepository<TableWithClob, Long> {
}
