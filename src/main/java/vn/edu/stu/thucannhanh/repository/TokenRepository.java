package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.thucannhanh.entities.Token;
public interface TokenRepository extends JpaRepository<Token,Long>{
    Token findByToken(String token);
}
