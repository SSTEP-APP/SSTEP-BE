package com.sstep.demo.store;


import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(long code); //코드 번호로 사업장 찾기

    @Query("SELECT s FROM Staff s WHERE s.store.id = :storeId ")
    Set<Staff> findStaffsByStoreId(@Param("storeId") Long storeId);

    //초대 코드를 받은 직원 리스트 출력
    @Query("SELECT t FROM Staff t WHERE t.store.id = :storeId and t.joinStatus = true and t.submitStatus = false")
    Set<Staff> findInviteStaffsByStoreId(Long storeId);

    //해당 사업장의 사장 찾기
    @Query("SELECT s FROM Staff s WHERE s.store.id = :storeId and s.ownerStatus = true ")
    Staff findOwnerById(Long storeId);

    //회원이 소속된 사업장 리스트 출력을 위한 기능
    @Query("SELECT s FROM Staff s WHERE s.member.id= :memberId")
    Set<Staff> findStaffsByMemberId(Long memberId);

    //코드 입력한 직원 리스트 출력
    @Query("SELECT s FROM Staff s WHERE s.store.id = :storeId and s.submitStatus = false and s.joinStatus = true ")
    Set<Staff> findInputCodeStaffsByStoreId(Long storeId);
}
