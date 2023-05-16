package com.sstep.demo.store;


import com.sstep.demo.member.domain.Member;
import com.sstep.demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> { //<Entity 클래스, PK 타입>
}
