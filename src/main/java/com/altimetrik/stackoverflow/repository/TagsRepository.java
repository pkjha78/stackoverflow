package com.altimetrik.stackoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.stackoverflow.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long>{

}
