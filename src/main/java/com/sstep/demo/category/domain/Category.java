package com.sstep.demo.category.domain;

import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    public void setStore(Store store) {
        this.store = store;
    }

    public void setCheckLists(Set<CheckList> checkLists) {
        this.checkLists.clear();
        if (checkLists != null) {
            this.checkLists.addAll(checkLists);
            checkLists.forEach(photo -> photo.setCategory(this));
        }
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private Set<CheckList> checkLists;

    @ManyToOne
    private Store store;
}
