package com.sstep.demo.store.domain;

import com.sstep.demo.category.domain.Category;
import com.sstep.demo.staff.domain.Staff;
import lombok.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //사업장 고유번호
    private String name; //사업장 이름
    private String address; //사업장 주소
    private String latitude; //사업장 위도 좌표
    private String longitude; //사업장 경도 좌표
    private boolean scale; //사업장 규모(5인이상: T, 이하: F)
    private boolean plan; //사업장 유료플랜 여부
    private long code; //사업장 코드번호 => 인앱 사업장 검색시 사용

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }

    public void setPlan(boolean plan) {
        this.plan = plan;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Store(long id, String name, String address, String latitude,
                 String longitude, boolean scale, boolean plan, long code) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.scale = scale;
        this.plan = plan;
        this.code = code;
    }

    public void setStaffList(Set<Staff> staffList) {
        this.staffList.clear();
        if (staffList != null) {
            this.staffList.addAll(staffList);
            staffList.forEach(staff -> staff.setStore(this));
        }
    }

    public void setCategories(Set<Category> categories) {
        this.categories.clear();
        if (categories != null) {
            this.categories.addAll(categories);
            categories.forEach(category -> category.setStore(this));
        }
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Staff> staffList;
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Category> categories;

}