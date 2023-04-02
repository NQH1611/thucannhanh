package vn.edu.stu.thucannhanh.service;

import vn.edu.stu.thucannhanh.entities.Token;

public interface TokenService {

    Token createToken(Token token);

    Token findByToken(String token);
}
