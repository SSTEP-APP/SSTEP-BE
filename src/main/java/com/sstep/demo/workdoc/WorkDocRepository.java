package com.sstep.demo.workdoc;

import com.sstep.demo.staff.domain.Staff;
import com.sstep.demo.workdoc.domain.WorkDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WorkDocRepository extends JpaRepository<WorkDoc, Long> {
    @Query("SELECT s from Staff s where s.workDoc.isFirstRegister = true and s.workDoc.isSecondRegister = false ")
    Set<Staff> findFirstRegStaffsByStoreId(Long storeId);

    @Query("SELECT s from Staff s where s.workDoc.isFirstRegister = false and s.workDoc.isSecondRegister = true ")
    Set<Staff> findSecondRegStaffsByStoreId(Long storeId);

    @Query("SELECT s from Staff s where s.workDoc.isFirstRegister = false and s.workDoc.isSecondRegister = false ")
    Set<Staff> findUnRegStaffsByStoreId(Long storeId);
}
