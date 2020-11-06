package com.altimetrik.stackoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.stackoverflow.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
