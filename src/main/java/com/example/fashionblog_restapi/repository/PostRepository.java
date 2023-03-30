package com.example.fashionblog_restapi.repository;

import com.example.fashionblog_restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
