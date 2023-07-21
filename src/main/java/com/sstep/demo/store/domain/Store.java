package com.sstep.demo.store.domain;

import com.sstep.demo.staff.domain.Staff;
import lombok.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
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
    @Column(nullable = false, unique = true)
    private long code; //사업장 코드번호 => 인앱 사업장 검색시 사용

    public Store(long id, String name, String address, String latitude,
                 String longitude, boolean scale, boolean plan, String payday, long code) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.scale = scale;
        this.plan = plan;
        this.payday = payday;
        this.code = code;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList.clear();
        if (staffList != null) {
            this.staffList.addAll(staffList);
            staffList.forEach(staff -> staff.setStore(this));
        }
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Staff> staffList;
}
