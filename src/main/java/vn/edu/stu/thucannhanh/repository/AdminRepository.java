package vn.edu.stu.thucannhanh.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.Admin;
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findByUsername(String username);
}
