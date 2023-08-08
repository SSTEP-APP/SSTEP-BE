package com.sstep.demo.healthdoc;

import com.sstep.demo.healthdoc.domain.HealthDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthDocRepository extends JpaRepository<HealthDoc, Long> {
    HealthDoc findByStaffId(Long staffId);
}
