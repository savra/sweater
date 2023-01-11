package com.example.sweater;

import com.example.sweater.domain.Post;
import com.example.sweater.repos.PostRepository;
import com.example.sweater.repos.TestHugeSelect;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;

class PostTest {

    @Autowired
    CacheManager cacheManager;
    @Autowired
    PostRepository postRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Test
    public void getPostByIdTest() {
      /*  cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
        Post p = postRepository.findPostById(1L);
        checkQueryCount(1);*/


    }

    private void checkQueryCount(long expected) {
        long actual = entityManagerFactory.unwrap(SessionFactory.class).getStatistics().getPrepareStatementCount();

        if (actual < expected) {
            System.out.println(String.format("Query count: %s less then expected: %s", actual, expected));
        }
    }
}