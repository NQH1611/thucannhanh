package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.stu.thucannhanh.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    @Query(value = "select * from chi_tiet_don_dat_hang where ma_don_dat_hang = :ma_don_dat_hang", nativeQuery =  true)
    OrderDetail findOrderDetailByOrderID(@Param("ma_don_dat_hang") int orderID);
}
