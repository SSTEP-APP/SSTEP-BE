package com.sstep.demo.workdoc;

import com.sstep.demo.workdoc.domain.WorkDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDocRepository extends JpaRepository<WorkDoc, Long> {
}
