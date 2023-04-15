package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findByUsername(String username);
}
