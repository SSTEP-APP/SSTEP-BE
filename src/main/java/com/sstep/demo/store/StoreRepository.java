package com.sstep.demo.store;


import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(long code); //코드 번호로 사업장 찾기

    @Query("SELECT s.staffList FROM Store s WHERE s.id = :storeId")
    Set<Staff> findStaffsByStoreId(@Param("storeId") Long storeId);

    //초대 코드를 받은 직원 리스트 출력
    @Query("SELECT s.staffList FROM Store s,Staff t WHERE s.id = :storeId and t.joinStatus = true")
    Set<Staff> findInviteStaffsByStoreId(Long storeId);

    //캘린더에 별도 추가로 추가 근무하는 직원 리스트 + 스케줄에 등록해 고정으로 근무하는 직원 리스트
    @Query("SELECT s.staffList FROM Store s,Calendar c,Schedule sc WHERE s.id = :storeId and " +
            "(c.calendarDate = :date or sc.weekDay = :day)")
    Set<Staff> findDayWorkStaffsByDate(Long storeId, Date date, DayOfWeek day);

    @Query("SELECT s.staffList FROM Store s,Commute c WHERE s.id = :storeId and c.disputeMessage is not null ")
    Set<Staff> findDisputeStaffsByExistMessage(Long storeId);

    //해당 사업장의 사장 찾기
    @Query("SELECT s.staffList FROM Store s, Staff st WHERE s.id = :storeId and st.ownerStatus = true")
    Staff findOwnerById(Long storeId);

    //회원이 소속된 사업장 리스트 출력을 위한 기능
    @Query("SELECT s FROM Staff s WHERE s.member.id= :memberId")
    Set<Staff> findStaffsByMemberId(Long memberId);

    //코드 입력한 직원 리스트 출력
    @Query("SELECT s.staffList FROM Store s,Staff t WHERE s.id = :storeId and t.submitStatus = true")
    Set<Staff> findInputCodeStaffsByStoreId(Long storeId);

    //해당 직원 조회
    @Query("SELECT s FROM Staff s WHERE s.id = :staffId")
    Staff findStaffByStaffId(Long staffId);
}
