package com.sstep.demo.staff;

import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> { //<Entity 클래스, PK 타입>
    // 직원 고유번호를 기반으로 직원 정보를 조회하는 메서드
    Staff findByIdAndStoreId(Long staffId, Long storeId);
}
