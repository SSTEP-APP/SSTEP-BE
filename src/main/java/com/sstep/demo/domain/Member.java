package com.sstep.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String user_num; //회원 고유번호
    private String user_id; //회원 아이디
    private String user_name; //회원명
    private String user_pn; //회원 전화번호
    private String user_pw; //비밀번호
    private String user_profile; //프로필 사진
    private boolean user_push1 ; //출퇴근 푸시 알람 여부
    private boolean user_push2 ; //근무일정 푸시 알람 여부
    private boolean user_push3 ; //해야할 일 푸시 알람 여부
    private boolean user_push4 ; //공지사항 푸시 알람 여부
    
    //Lombok 애노테이션으로 Getter & Setter 생성
}
