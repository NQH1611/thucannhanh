package vn.edu.stu.thucannhanh.service;

import vn.edu.stu.thucannhanh.security.UserPrincipal;

public interface AdminLogin {
    UserPrincipal findByUsername(String username);
}
