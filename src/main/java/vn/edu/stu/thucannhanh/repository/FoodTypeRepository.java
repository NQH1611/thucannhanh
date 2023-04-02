package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer>{
    
}
