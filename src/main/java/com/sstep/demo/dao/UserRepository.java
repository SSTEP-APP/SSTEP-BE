package com.sstep.demo.dao;

import com.sstep.demo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User save(User user);
}
