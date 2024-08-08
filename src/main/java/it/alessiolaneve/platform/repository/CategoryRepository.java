package it.alessiolaneve.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.alessiolaneve.platform.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String categoryName);
}


