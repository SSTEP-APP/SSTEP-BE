package com.sstep.demo.checklistmanager;

import com.sstep.demo.checklistmanager.domain.CheckListManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckListManagerRepository extends JpaRepository<CheckListManager, Long> {
    @Query("SELECT c FROM CheckListManager c WHERE c.checkList.id = :checkListId ")
    Set<CheckListManager> findCheckListManagersByCheckListId(Long checkListId);

    @Query("SELECT c FROM CheckListManager c WHERE c.staff.id = :staffId ")
    Set<CheckListManager> findCheckListManagersByStaffId(long staffId);
}
