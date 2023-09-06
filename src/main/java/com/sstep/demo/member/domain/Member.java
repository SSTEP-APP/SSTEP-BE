package com.sstep.demo.member.domain;

import com.sstep.demo.staff.domain.Staff;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Member {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //회원 고유번호
    private String username; //회원 아이디
    private String name; //회원명
    private String phoneNum; //회원 전화번호
    private String password; //비밀번호

    public void setStaffList(Set<Staff> staffList) {
        this.staffList.clear();
        if (staffList != null) {
            this.staffList.addAll(staffList);
            staffList.forEach(staff -> staff.setMember(this));
        }
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<Staff> staffList ;
}


