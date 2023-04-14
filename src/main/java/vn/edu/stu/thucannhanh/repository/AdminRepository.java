package vn.edu.stu.thucannhanh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findByUsername(String username);
}
