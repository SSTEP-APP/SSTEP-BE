package com.sstep.demo.store.domain;

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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //사업장 고유번호

    @Column(nullable = false)
    private String name; //사업장 이름

    @Column(nullable = false)
    private String address; //사업장 주소
    @Column(nullable = false)
    private String latitude; //사업장 위도 좌표
    @Column(nullable = false)
    private String longitude; //사업장 경도 좌표
    @Column(nullable = false)
    private boolean scale; //사업장 규모(5인이상: T, 이하: F)
    @Column(nullable = false)
    private boolean plan; //사업장 유료플랜 여부
    private String payday; //사업장 급여날

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Staff> staffList;
}
