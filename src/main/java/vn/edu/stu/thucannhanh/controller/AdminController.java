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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.Admin;
import vn.edu.stu.thucannhanh.entities.Role;
import vn.edu.stu.thucannhanh.entities.Token;
import vn.edu.stu.thucannhanh.repository.AdminRepository;
import vn.edu.stu.thucannhanh.repository.RoleRepository;
import vn.edu.stu.thucannhanh.security.JwtUtil;
import vn.edu.stu.thucannhanh.security.UserPrincipal;
import vn.edu.stu.thucannhanh.service.AdminLogin;
import vn.edu.stu.thucannhanh.service.TokenService;

@RestController
@CrossOrigin(maxAge = 3600)
public class AdminController {
    @Autowired
    private AdminRepository gAdminRepository;
    @Autowired
    private AdminLogin adminLogin;
    @Autowired
    private TokenService gTokenService;

    @Autowired
    private RoleRepository gRoleRepository;

    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        List<Admin> lstadmin = new ArrayList<>();
        gAdminRepository.findAll().forEach(lstadmin::add);
        return new ResponseEntity<>(lstadmin, HttpStatus.OK);
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id){
        Optional<Admin> admin = gAdminRepository.findById(id);
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);
    }
    @PostMapping("/loginadmin")
    public ResponseEntity<?> login(@RequestBody UserPrincipal user) {
        Admin admin = gAdminRepository.findByUsername(user.getUsername());
        if(admin != null){
            UserPrincipal userPrincipal = adminLogin.findByUsername(user.getUsername());
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
    @PostMapping("/register")
    public ResponseEntity<?> createadmin( @RequestBody Admin admin){
        try {
            Admin newAdmin = new Admin();
            Set<Role> roleSeller = new HashSet<>();
            roleSeller.add(gRoleRepository.findByRoleKey("ROLE_ADMIN"));
            newAdmin.setName(admin.getName());
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setPhoneNumber(admin.getPhoneNumber());
            newAdmin.setUsername(admin.getUsername());
            newAdmin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            newAdmin.setRoles(roleSeller);
            Admin testUsername = gAdminRepository.findByUsername(newAdmin.getUsername());
            if(testUsername != null) return new ResponseEntity<>("Username của bạn đã tồn tại!", HttpStatus.PARTIAL_CONTENT);
            Admin save = gAdminRepository.save(newAdmin);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Admin: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<Object> updateEmployee( @RequestBody Admin admin, @PathVariable("id") int id){
        try {
            Optional<Admin> result = gAdminRepository.findById(id);
            if(result.isPresent()){
                Admin newAdmin = result.get();
                newAdmin.setName(admin.getName());
                newAdmin.setEmail(admin.getEmail());
                newAdmin.setPhoneNumber(admin.getPhoneNumber());
                Admin saveAdmin = gAdminRepository.save(newAdmin);
                return new ResponseEntity<>(saveAdmin, HttpStatus.OK);

            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Admin: " + e.getCause().getCause().getMessage());
        }
    }
    @GetMapping("/admintoken")
    public ResponseEntity<?> getAdminByToken(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated()){
                return new ResponseEntity<>("No Authenicated user found", HttpStatus.UNAUTHORIZED);
            }
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Admin admin = gAdminRepository.findByUsername(name);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
