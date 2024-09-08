package com.hoavd.fbstore.product.repository;

import com.hoavd.fbstore.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
  @Query(value = "select * from product p where "
    + "(p.name like concat('%', :name, '%') or :name is null or :name='') "
    + "and (p.category_id = :category or 0 = :category) "
    + "and p.is_deleted = false order by p.created_at desc", nativeQuery = true)
  Page<Product> getPageListProduct(@Param("name") String name, @Param("category") long category, Pageable pageable);

  @Query(value = "select * from product p where" +
    " (:name is null or :name = '' or lower(p.name) like lower(concat('%',:name,'%')))" +
    " order by p.created_at asc", nativeQuery = true)
  List<Product> getList(@Param("name") String name);

  Product findById(long id);

  Product findByName(String name);
}
