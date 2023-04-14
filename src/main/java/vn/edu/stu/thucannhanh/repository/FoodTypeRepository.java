package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.FoodType;
@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer>{
    
}
