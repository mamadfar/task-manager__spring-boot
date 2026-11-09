package com.leetjourney.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leetjourney.taskmanager.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
