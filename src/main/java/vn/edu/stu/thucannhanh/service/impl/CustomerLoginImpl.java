package vn.edu.stu.thucannhanh.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.thucannhanh.entities.Customer;
import vn.edu.stu.thucannhanh.repository.CustomerRepository;
import vn.edu.stu.thucannhanh.security.UserPrincipal;
import vn.edu.stu.thucannhanh.service.CustomerLogin;
@Service
public class CustomerLoginImpl implements CustomerLogin{
    @Autowired
    private CustomerRepository gCustomerRepository;

    @Override
    public UserPrincipal findByUsername(String username) {
        Customer customer = gCustomerRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != customer) {
            Set<String> authorities = new HashSet<>();
            if (null != customer.getRoles()) customer.getRoles().forEach(r -> {
                authorities.add(r.getRoleKey());
                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
            });

            userPrincipal.setUserId(customer.getId());
            userPrincipal.setUsername(customer.getUsername());
            userPrincipal.setPassword(customer.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
