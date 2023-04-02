package vn.edu.stu.thucannhanh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.OrderDetail;
import vn.edu.stu.thucannhanh.repository.OrderDetailRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class OrderDetailController {
    @Autowired
    private OrderDetailRepository gOrderDetailRepository;

    @GetMapping("/orderdetail")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetail(){
        List<OrderDetail> lstOrderDetail = new ArrayList<>();
        gOrderDetailRepository.findAll().forEach(lstOrderDetail::add);
        return new ResponseEntity<>(lstOrderDetail, HttpStatus.OK);
    }
    @GetMapping("/orderdetail/order/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailByOrderID(@PathVariable("id") int id){
        OrderDetail lstOrderDetail = gOrderDetailRepository.findOrderDetailByOrderID(id);
        return new ResponseEntity<>(lstOrderDetail, HttpStatus.OK);
    }

    @GetMapping("/orderdetail/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailByID(@PathVariable("id") int id){
        Optional<OrderDetail> orderDetail = gOrderDetailRepository.findById(id);
        return new ResponseEntity<>(orderDetail.get(), HttpStatus.OK);
    }

    @PostMapping("/orderdetail")
    public ResponseEntity<?> createOrderĐetail(@RequestBody OrderDetail orderDetail){
        try {
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setFood(orderDetail.getFood());
            newOrderDetail.setOrder(orderDetail.getOrder());
            newOrderDetail.setQuantity(orderDetail.getQuantity());
            OrderDetail save = gOrderDetailRepository.save(newOrderDetail);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
            .body("Failed to Create specified OrderDetail: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/orderdetail/{id}")
    public ResponseEntity<?> createOrderĐetail(@RequestBody OrderDetail orderDetail, @PathVariable("id") int id){
        try {
            Optional<OrderDetail> result = gOrderDetailRepository.findById(id);
            if(result.isPresent()){
                OrderDetail newOrderDetail = result.get();
                newOrderDetail.setFood(orderDetail.getFood());
                newOrderDetail.setOrder(orderDetail.getOrder());
                newOrderDetail.setQuantity(orderDetail.getQuantity());
                OrderDetail save = gOrderDetailRepository.save(newOrderDetail);
                return new ResponseEntity<>(save, HttpStatus.CREATED);
            } else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        }catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                                    .body("Failed to Update specified OrderDetail: " + e.getCause().getCause().getMessage());
        }
        
        
    }
}
