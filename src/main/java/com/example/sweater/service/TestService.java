package com.example.sweater.service;

import com.example.sweater.domain.TestBulkInsert;
import com.example.sweater.repos.TestBulkInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class TestService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private TestBulkInsertRepository testBulkInsertRepository;

    @Autowired
    private JdbcTemplate template;

  /*  @Transactional
   public void saveData(List<TestBulkInsert> testBulkInsertList) {
        for (TestBulkInsert entity : testBulkInsertList) {
            entityManager.persist(entity);
        }
        entityManager.flush();
        entityManager.clear();
    }*/

    @Transactional
    public void saveData(List<TestBulkInsert> testBulkInsertList) {
        template.batchUpdate("insert into table_bulk_insert (id, value) values (table_bulk_insert_seq.nextval, ?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, testBulkInsertList.get(i).getValue());
            }

            @Override
            public int getBatchSize() {
                return testBulkInsertList.size();
            }
        });
    }
}
