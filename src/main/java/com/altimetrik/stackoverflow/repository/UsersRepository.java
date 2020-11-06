package com.altimetrik.stackoverflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.stackoverflow.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByDisplayName(String displayName);
}
