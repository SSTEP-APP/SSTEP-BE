package com.sstep.demo.store;


import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(long code); //코드 번호로 사업장 찾기

    @Query("SELECT s.staffList FROM Store s WHERE s.id = :storeId")
    List<Staff> findStaffsByStoreId(@Param("storeId") Long storeId);

    // 매장에 직원 추가를 위한 메소드 추가
    @Transactional
    @Modifying
    @Query("UPDATE Store s SET s.staffList = :employees WHERE s.id = :storeId")
    void addStaffsToStore(@Param("storeId") Long storeId, @Param("staffList") List<Staff> staffList);

    @Query("SELECT s.staffList FROM Store s,Staff t WHERE s.id = :storeId and t.joinStatus = false")
    List<Staff> findUnRegStaffsByStoreId(Long storeId);


}
