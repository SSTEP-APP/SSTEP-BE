package com.sstep.demo.store;


import com.sstep.demo.member.domain.Member;
import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(long code); //코드 번호로 사업장 찾기

    @Query("SELECT s.staffs FROM Store s WHERE s.id = :storeId")
    List<Staff> findStaffsByStoreId(@Param("storeId") Long storeId);
}
