package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
		Optional<Category> findById(Long id);
}
