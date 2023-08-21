package com.sstep.demo.staff;

import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("select s from Staff s where s.store.code = :storeCode and s.member.username = :username")
    Staff findByUsernameAndStoreCode(String username, Long storeCode);
}
