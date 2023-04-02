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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.Order;
import vn.edu.stu.thucannhanh.repository.OrderRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderRepository gOrderRepository;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> lstOrder = new ArrayList<>();
        gOrderRepository.findAll().forEach(lstOrder::add);
        return new ResponseEntity<>(lstOrder, HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable("id") int id){
        Optional<Order> order = gOrderRepository.findById(id);
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order newOrder = new Order();
            newOrder.setCustomer(order.getCustomer());
            newOrder.setAdmin(order.getAdmin());
            newOrder.setCreateDate(new Date());
            newOrder.setTotal(order.getTotal());
            newOrder.setStatus(1);
            Order save = gOrderRepository.save(newOrder);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Order: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable("id") int id){
        try {
            Optional<Order> result = gOrderRepository.findById(id);
            if(result.isPresent()){
                if(result.get().getStatus() != 2){
                    Order newOrder = result.get();
                    newOrder.setCustomer(order.getCustomer());
                    newOrder.setAdmin(order.getAdmin());
                    newOrder.setCreateDate(new Date());
                    newOrder.setTotal(order.getTotal());
                    Order save = gOrderRepository.save(newOrder);
                    return new ResponseEntity<>(save, HttpStatus.CREATED);
                }
                return new ResponseEntity<>("Status Accept, Cound't update", HttpStatus.BAD_REQUEST);
                
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified order: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/order/status/accept/{id}")
    public ResponseEntity<?> updateStatusOrder(@RequestBody Order order, @PathVariable("id") int id){
        try {
            Optional<Order> result = gOrderRepository.findById(id);
            if(result.isPresent()){
                Order newOrder = result.get();
                newOrder.setStatus(2);
                Order save = gOrderRepository.save(newOrder);
                return new ResponseEntity<>(save, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Order: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/order/delete/{id}")
    public ResponseEntity<?> deleteOrder( @PathVariable("id") int id){
        Optional<Order> order = gOrderRepository.findById(id);
        if(order.isPresent()){
            Order newOrder = order.get();
            newOrder.setStatus(0);
            Order save = gOrderRepository.save(newOrder);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
