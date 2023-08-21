package com.sstep.demo.healthdoc;

import com.sstep.demo.healthdoc.domain.HealthDoc;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthDocRepository extends JpaRepository<HealthDoc, Long> {
    @Query("select h from HealthDoc h where h.staff.id = :staffId")
    HealthDoc findByStaffId(Long staffId);
}
