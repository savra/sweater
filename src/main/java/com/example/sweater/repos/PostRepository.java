package com.example.sweater.repos;

import com.example.sweater.domain.ConstructorProjectionDto;
import com.example.sweater.domain.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import javax.persistence.Tuple;
import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {
    Post findPostById(Long id);

    @Query(value = "SELECT a_1 AS a1,\n" +
            " a_2 AS a2,\n" +
            " a_3 AS a3,\n" +
            " a_4 AS a4,\n" +
            " a_5 AS a5,\n" +
            " a_6 AS a6,\n" +
            " a_7 AS a7,\n" +
            " a_8 AS a8,\n" +
            " a_9 AS a9,\n" +
            " a_10 AS a10,\n" +
            " a_11 AS a11,\n" +
            " a_12 AS a12,\n" +
            " a_13 AS a13,\n" +
            " a_14 AS a14,\n" +
            " a_15 AS a15,\n" +
            " a_16 AS a16,\n" +
            " a_17 AS a17,\n" +
            " a_18 AS a18,\n" +
            " a_19 AS a19,\n" +
            " a_20 AS a20 FROM huge_table where ROWNUM <= 1000", nativeQuery = true)
    List<TestHugeSelect> getHugeSelect();

    @Query(value = "SELECT a_1 AS a1,\n" +
            " a_2 AS a2,\n" +
            " a_3 AS a3,\n" +
            " a_4 AS a4,\n" +
            " a_5 AS a5,\n" +
            " a_6 AS a6,\n" +
            " a_7 AS a7,\n" +
            " a_8 AS a8,\n" +
            " a_9 AS a9,\n" +
            " a_10 AS a10,\n" +
            " a_11 AS a11,\n" +
            " a_12 AS a12,\n" +
            " a_13 AS a13,\n" +
            " a_14 AS a14,\n" +
            " a_15 AS a15,\n" +
            " a_16 AS a16,\n" +
            " a_17 AS a17,\n" +
            " a_18 AS a18,\n" +
            " a_19 AS a19,\n" +
            " a_20 AS a20 FROM huge_table where ROWNUM <= 1000", nativeQuery = true)
    List<Tuple> findAllNameOnlyTupleProjection();
}

