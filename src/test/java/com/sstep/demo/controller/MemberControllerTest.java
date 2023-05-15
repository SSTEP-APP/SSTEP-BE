package com.sstep.demo.controller;

import com.sstep.demo.member.dao.MemberRepository;
import com.sstep.demo.member.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberControllerTest {

    MemberRepository memberRepository;

    @Autowired
    MemberControllerTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Test
    void save() {
        //given
        User user = new User("hi", "hi", "hi", "hi", "9", true, true, true, true);

        //when
        User savedUser = memberRepository.save(user);

        //then
        Optional<User> findUser = memberRepository.findById(user.getUserNum());
        assertThat(findUser).isEqualTo(savedUser);
    }


}