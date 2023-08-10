package com.sstep.demo.member.domain;

import com.sstep.demo.staff.domain.Staff;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Member {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //회원 고유번호
    @Column(nullable = false, length = 30, unique = true)
    private String username; //회원 아이디
    @Column(nullable = false, length = 10)
    private String name; //회원명

    @Column(nullable = false, length = 20)
    private String phoneNum; //회원 전화번호

    @Column(nullable = false, length = 30)
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

    public void setStaffList(List<Staff> staffList) {
        this.staffList.clear();
        if (staffList != null) {
            this.staffList.addAll(staffList);
            staffList.forEach(staff -> staff.setMember(this));
        }
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Staff> staffList ;
}


