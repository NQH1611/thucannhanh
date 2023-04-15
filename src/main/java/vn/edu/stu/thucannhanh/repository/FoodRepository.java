package vn.edu.stu.thucannhanh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.edu.stu.thucannhanh.entities.Food;
public interface FoodRepository extends JpaRepository<Food, Integer>{
    @Query(value = "select * from thuc_an where trang_thai = 1", nativeQuery = true)
    List<Food> findAllStatus1();
}
