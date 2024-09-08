package com.hoavd.fbstore.product.repository;

import com.hoavd.fbstore.product.entity.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
  @Query(value = "select * from producer p order by p.name desc", nativeQuery = true)
  Page<Producer> getPageList(Pageable pageable);

  Producer findById(long id);

  Producer findByName(String name);
}
