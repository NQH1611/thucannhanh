package vn.edu.stu.thucannhanh.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import vn.edu.stu.thucannhanh.entities.Customer;
import vn.edu.stu.thucannhanh.entities.Role;
import vn.edu.stu.thucannhanh.entities.Token;
import vn.edu.stu.thucannhanh.repository.CustomerRepository;
import vn.edu.stu.thucannhanh.repository.RoleRepository;
import vn.edu.stu.thucannhanh.security.JwtUtil;
import vn.edu.stu.thucannhanh.security.UserPrincipal;
import vn.edu.stu.thucannhanh.service.CustomerLogin;
import vn.edu.stu.thucannhanh.service.TokenService;

@RestController
@CrossOrigin(maxAge = 3600)
public class CustomerController {
    @Autowired
    private CustomerRepository gCustomerRepository;
    @Autowired
    private CustomerLogin customerLogin;
    @Autowired
    private TokenService gTokenService;
    @Autowired
    private RoleRepository gRoleRepository;
    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> lstadmin = new ArrayList<>();
        gCustomerRepository.findAll().forEach(lstadmin::add);
        return new ResponseEntity<>(lstadmin, HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        Optional<Customer> customer = gCustomerRepository.findById(id);
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
    @PostMapping("/customer/login")
    public ResponseEntity<?> login(@RequestBody UserPrincipal user) {
        Customer customer = gCustomerRepository.findByUsername(user.getUsername());
        if(customer != null){
            UserPrincipal userPrincipal = customerLogin.findByUsername(user.getUsername());
            if (new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword()) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
            }
            Token token = new Token();
            token.setToken(JwtUtil.generateToken(userPrincipal));
            token.setTokenExpDate(JwtUtil.generateExpirationDate());
            token.setCreatedBy(userPrincipal.getUserId());
            gTokenService.createToken(token);
            return ResponseEntity.ok(token.getToken());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản của bạn không tồn tại");
        }
       
    }
    @PostMapping("/customer/register")
    public ResponseEntity<?> createadmin( @RequestBody Customer customer){
        try {
            Customer newCustomer = new Customer();
            Set<Role> roleSeller = new HashSet<>();
            roleSeller.add(gRoleRepository.findByRoleKey("ROLE_CUSTOMER"));
            newCustomer.setName(customer.getName());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            newCustomer.setUsername(customer.getUsername());
            newCustomer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
            newCustomer.setImage(customer.getImage());
            newCustomer.setStatus(1);
            newCustomer.setRoles(roleSeller);
            Customer testUsername = gCustomerRepository.findByUsername(customer.getUsername());
            if(testUsername != null) return new ResponseEntity<>("Username của bạn đã tồn tại!", HttpStatus.PARTIAL_CONTENT);
            Customer save = gCustomerRepository.save(newCustomer);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified admin: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/customer/{id}")
    public ResponseEntity<Object> updateEmployee( @RequestBody Customer customer, @PathVariable("id") int id){
        try {
            Optional<Customer> result = gCustomerRepository.findById(id);
            if(result.isPresent()){
                Customer newCustomer = result.get();
                newCustomer.setName(customer.getName());
                newCustomer.setEmail(customer.getEmail());
                newCustomer.setPhoneNumber(customer.getPhoneNumber());
                newCustomer.setUsername(customer.getUsername());
                newCustomer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
                newCustomer.setImage(customer.getImage());
                newCustomer.setStatus(1);
                Customer save = gCustomerRepository.save(newCustomer);
                return new ResponseEntity<>(save, HttpStatus.OK);

            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Controller: " + e.getCause().getCause().getMessage());
        }
    }
    @GetMapping("/customertoken")
    public ResponseEntity<?> getAdminByToken(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated()){
                return new ResponseEntity<>("No Authenicated user found", HttpStatus.UNAUTHORIZED);
            }
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = gCustomerRepository.findByUsername(name);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("customer/delete")
    public ResponseEntity<Object> deleteEmployee(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated()){
                return new ResponseEntity<>("No Authenicated user found", HttpStatus.UNAUTHORIZED);
            }
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer result = gCustomerRepository.findByUsername(name);
            if(result != null){
                result.setStatus(0);
                Customer save = gCustomerRepository.save(result);
                return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Delete specified Customer: " + e.getCause().getCause().getMessage());
        }
    }
}
