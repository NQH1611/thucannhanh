package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
