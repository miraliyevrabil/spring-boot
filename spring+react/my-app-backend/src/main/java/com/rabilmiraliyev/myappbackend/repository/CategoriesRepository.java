package com.rabilmiraliyev.myappbackend.repository;

import com.rabilmiraliyev.myappbackend.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
}
