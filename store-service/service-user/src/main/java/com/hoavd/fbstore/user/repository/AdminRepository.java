package com.hoavd.fbstore.user.repository;


import com.hoavd.fbstore.user.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
  @Query(value = "select * from admin a order by a.created_at desc", nativeQuery = true)
  Page<Admin> getPageList(Pageable pageable);

  Admin findById(long id);

  Admin findByUsername(String username);

  Admin findByIdAndUsername(long id, String username);
}
