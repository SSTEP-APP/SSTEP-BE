package com.sstep.demo.staff;

import com.sstep.demo.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> { //<Entity 클래스, PK 타입>
}
