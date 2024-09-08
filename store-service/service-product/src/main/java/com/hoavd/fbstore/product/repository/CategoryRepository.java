package com.hoavd.fbstore.product.repository;


import com.hoavd.fbstore.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query(value = "select * from category c order by c.name desc", nativeQuery = true)
  Page<Category> getPageListCategory(Pageable pageable);

  Category findById(long id);

  Category findByName(String name);
}
