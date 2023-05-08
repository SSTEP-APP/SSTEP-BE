package com.sstep.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "staff")
public class Staff {
    @Id //기본키
    @Column(name = "staff_num")
    private String staffNum; //직원 고유번호
    @Column(name = "staff_invite")
    private int staffInvite; //직원 초대현왕

    //회원 테이블과 1대다 조인
    @ManyToOne
    @JoinColumn(name = "user_num")
    private User user;
}
