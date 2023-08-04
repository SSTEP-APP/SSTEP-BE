package com.sstep.demo.checklistmanager;

import com.sstep.demo.checklistmanager.domain.CheckListManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListManagerRepository extends JpaRepository<CheckListManager, Long> {
}
