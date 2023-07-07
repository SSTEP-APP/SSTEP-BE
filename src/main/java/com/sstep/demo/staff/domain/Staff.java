package com.sstep.demo.staff.domain;

import com.sstep.demo.member.domain.Member;
import com.sstep.demo.store.domain.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class Staff {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 DB에 위임 => DB에서 AUTO_INCREMENT 기능 사용
    private long id; //직원 고유번호
    @Column(nullable = false)
    private int staffInvite; //직원 초대현황
    @Column(nullable = false)
    private Date startDay; //입사일
    @Column(nullable = false)
    private int hourMoney; //시급
    @Column(nullable = false, columnDefinition = "INT default 0")
    private int lateCount; //지각 횟수
    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    private boolean ownerStatus; //사장 여부

    //회원 테이블과 1대다 조인
    @ManyToOne
    private Member member;

    @ManyToMany(mappedBy = "includedStaff")
    private Set<Store> include;
}
