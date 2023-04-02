package vn.edu.stu.thucannhanh.service;

import vn.edu.stu.thucannhanh.security.UserPrincipal;

public interface CustomerLogin {
    UserPrincipal findByUsername(String username);
}
