package com.example.webTeam.validation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webTeam.validation.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// @Query ("SELECT u FROM ModelName u Where u.email = :email")
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);
}
