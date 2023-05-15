package com.sstep.demo.staff.domain;

import com.sstep.demo.member.domain.Member;
import com.sstep.demo.store.domain.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class Staff {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //직원 고유번호
    private int staffInvite; //직원 초대현황

    //회원 테이블과 1대다 조인
    @ManyToOne
    private Member member;

    @ManyToOne
    private Store store;
}
