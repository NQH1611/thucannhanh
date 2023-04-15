package vn.edu.stu.thucannhanh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.stu.thucannhanh.entities.Feedback;
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
    @Query(value = "select * from phan_hoi where ma_khach_hang = :ma_khach_hang", nativeQuery = true)
    List<Feedback> findFeedbackByCustomer(@Param("ma_khach_hang") int customer);
    @Query(value = "select * from phan_hoi where ma_thuc_an = :ma_thuc_an", nativeQuery = true)
    List<Feedback> findFeedbackByFoodId(@Param("ma_thuc_an") int customer);
}
