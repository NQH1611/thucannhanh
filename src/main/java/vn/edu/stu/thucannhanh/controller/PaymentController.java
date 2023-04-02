package vn.edu.stu.thucannhanh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.Payment;
import vn.edu.stu.thucannhanh.repository.PaymentRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class PaymentController {
    @Autowired
    private PaymentRepository gPaymentRepository;

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getAllPayment(){
        List<Payment> lstPayment = new ArrayList<>();
        gPaymentRepository.findAll().forEach(lstPayment::add);
        return new ResponseEntity<>(lstPayment, HttpStatus.OK);
    }
    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getAllPayment(@PathVariable("id") int id){
        Optional<Payment> payment = gPaymentRepository.findById(id);
        return new ResponseEntity<>(payment.get(), HttpStatus.OK);
    }
    @GetMapping("/payment/order/{id}")
    public ResponseEntity<Payment> getPaymentByOrderID(@PathVariable("id") int id){
        Payment payment = gPaymentRepository.findPaymentByOrderID(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    @PostMapping("/payment")
    public ResponseEntity<?> createOrderĐetail(@RequestBody Payment payment){
        try {
            Payment newPayment = new Payment();
            newPayment.setOrder(payment.getOrder());
            newPayment.setCreateDate(new Date());
            newPayment.setPaymentType(payment.getPaymentType());
            newPayment.setTotal(payment.getTotal());
            Payment save = gPaymentRepository.save(newPayment);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
            .body("Failed to Create specified OrderDetail: " + e.getCause().getCause().getMessage());
        }
    }
}
