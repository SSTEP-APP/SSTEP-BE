package com.sstep.demo.dao;

import com.sstep.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> { //<Entity 클래스, PK 타입>
    User findByUserNum(String userNum);
}
