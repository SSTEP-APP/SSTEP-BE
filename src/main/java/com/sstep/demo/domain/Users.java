package com.sstep.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
    private String userNum; //회원 고유번호
    private String userId; //회원 아이디
    private String userName; //회원명
    private String userPn; //회원 전화번호
    private String userPw; //비밀번호
    private String userProfile; //프로필 사진
    private boolean userPush1 ; //출퇴근 푸시 알람 여부
    private boolean userPush2 ; //근무일정 푸시 알람 여부
    private boolean userPush3 ; //해야할 일 푸시 알람 여부
    private boolean userPush4 ; //공지사항 푸시 알람 여부
    
    //Lombok 애노테이션으로 Getter & Setter 생성
}
