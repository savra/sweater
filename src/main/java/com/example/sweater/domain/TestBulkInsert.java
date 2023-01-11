package com.example.sweater.domain;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "table_bulk_insert")
public class TestBulkInsert {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_bulk_insert_seq")
    @SequenceGenerator(name = "table_bulk_insert_seq", sequenceName = "table_bulk_insert_seq", allocationSize = 1)
    Long id;

    @Column(name = "value")
    private long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
