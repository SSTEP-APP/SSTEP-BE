package com.sstep.demo.category.domain;

import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //카테고리 고유번호
    private String name; //카테고리 명

    public String getName() {
        return name;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne
    private CheckList checkList;

    @ManyToOne
    private Store store;
}
