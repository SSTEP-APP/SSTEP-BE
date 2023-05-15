package com.sstep.demo.member.domain;

import com.sstep.demo.staff.domain.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //회원 고유번호
    private String memberId; //회원 아이디

    private String name; //회원명

    private String phoneNum; //회원 전화번호

    private String password; //비밀번호

//    private String profileImage; //프로필 사진

//    @Column(name = "user_push1")
//    private boolean userPush1; //출퇴근 푸시 알람 여부
//
//    @Column(name = "user_push2")
//    private boolean userPush2; //근무일정 푸시 알람 여부
//
//    @Column(name = "user_push3")
//    private boolean userPush3; //해야할 일 푸시 알람 여부
//
//    @Column(name = "user_push4")
//    private boolean userPush4; //공지사항 푸시 알람 여부

    //Lombok 애노테이션으로 Getter & Setter 생성

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Staff> staffList;


}


