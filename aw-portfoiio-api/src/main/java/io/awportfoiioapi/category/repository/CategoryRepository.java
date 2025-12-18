package io.awportfoiioapi.category.repository;

import io.awportfoiioapi.category.entity.Category;
import io.awportfoiioapi.category.repository.query.CategoryQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryQueryRepository {

}
