package vn.edu.stu.thucannhanh.service.impl;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.thucannhanh.entities.Admin;
import vn.edu.stu.thucannhanh.repository.AdminRepository;
import vn.edu.stu.thucannhanh.security.UserPrincipal;
import vn.edu.stu.thucannhanh.service.AdminLogin;

@Service
public class AdminLoginImpl implements AdminLogin{
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserPrincipal findByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != admin) {
            Set<String> authorities = new HashSet<>();
            if (null != admin.getRoles()) admin.getRoles().forEach(r -> {
                authorities.add(r.getRoleKey());
                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
            });

            userPrincipal.setUserId(admin.getId());
            userPrincipal.setUsername(admin.getUsername());
            userPrincipal.setPassword(admin.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
