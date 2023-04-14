package vn.edu.stu.thucannhanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.stu.thucannhanh.entities.Token;
@Repository
public interface TokenRepository extends JpaRepository<Token,Long>{
    Token findByToken(String token);
}
