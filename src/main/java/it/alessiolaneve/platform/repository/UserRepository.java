package it.alessiolaneve.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.alessiolaneve.platform.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

}
