package com.sstep.demo.healthdoc;

import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HealthDocRepository extends JpaRepository<HealthDoc, Long> {
    @Query("select h from HealthDoc h where h.staff.id = :staffId")
    HealthDoc findByStaffId(Long staffId);

    @Query("select h from HealthDoc h where h.staff.store.id = :storeId and h.isRegister = true ")
    Set<HealthDoc> findHealthDocsByStoreIdAndRegister(Long storeId);

    @Query("select s from Staff s where s.store.id = :storeId and s.healthDoc.isRegister = false ")
    Set<Staff> findStaffsByStoreIdAndUnRegister(Long storeId);
}
