package com.sstep.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id //기본키
    @Column(name = "user_num")
    private String user_num; //회원 고유번호
    @Column(name = "user_id")
    private String user_id; //회원 아이디

    @Column(name = "user_name")
    private String userName; //회원명

    @Column(name = "user_pn")
    private String userPn; //회원 전화번호

    @Column(name = "user_pw")
    private String userPw; //비밀번호

    @Column(name = "user_profile")
    private String userProfile; //프로필 사진

    @Column(name = "user_push1")
    private boolean userPush1; //출퇴근 푸시 알람 여부

    @Column(name = "user_push2")
    private boolean userPush2; //근무일정 푸시 알람 여부

    @Column(name = "user_push3")
    private boolean userPush3; //해야할 일 푸시 알람 여부

    @Column(name = "user_push4")
    private boolean userPush4; //공지사항 푸시 알람 여부


    //Lombok 애노테이션으로 Getter & Setter 생성
}
