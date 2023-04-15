package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.Role;
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRoleKey(String roleKey);
}
