package com.sstep.demo.dao;

import com.sstep.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>

    {
        User save(User user);

        User findByUSER_ID (String user_id); //이메일로 user 조회

        List<User> findAll (); //전체 조회
    }
