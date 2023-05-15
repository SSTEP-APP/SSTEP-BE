package com.sstep.demo.controller;

import com.sstep.demo.domain.User;
import com.sstep.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController(value = "/user")
public class UserController {
    private final UserService userService;

    //회원정보 저장
    @PostMapping("/reg")
    public ResponseEntity<User> Register(@RequestBody User user) {
        User saveUser = userService.save(user);
        return ResponseEntity.ok().body(saveUser);
    }

    //회원 조회
    @GetMapping("/{userNum}")
    public ResponseEntity<User> findById(@PathVariable(value = "userNum") String userNum) {
        System.out.println(userNum);
        User findUser = userService.findById(userNum);
        if (findUser != null) {
            return ResponseEntity.ok().body(findUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //회원정보 수정
    @PutMapping("/{num}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "num") String userNum) {
        User findUser = userService.findById(userNum);
        if (findUser != null) {
            User updateUser = userService.update(findUser);
            return ResponseEntity.ok().body(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //회원 삭제
    @DeleteMapping("/{num}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "num") String userNum) {
        User findUser = userService.findById(userNum);
        if (findUser != null) {
            userService.delete(findUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
