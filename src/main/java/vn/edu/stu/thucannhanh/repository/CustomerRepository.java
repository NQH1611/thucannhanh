package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findByUsername(String username);
}
