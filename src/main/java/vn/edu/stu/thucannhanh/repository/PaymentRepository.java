package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
    @Query(value = "select * from thanh_toan where ma_don_hang = :ma_don_hang", nativeQuery =  true)
    Payment findPaymentByOrderID(@Param("ma_don_hang") int orderID);
}
