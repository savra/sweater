package com.example.sweater.repos;

import com.example.sweater.domain.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
