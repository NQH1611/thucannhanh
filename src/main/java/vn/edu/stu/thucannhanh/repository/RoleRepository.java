package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRoleKey(String roleKey);
}
