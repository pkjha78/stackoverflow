package com.altimetrik.stackoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.stackoverflow.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{

}
