package com.sstep.demo.checklist;

import com.sstep.demo.checklist.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    @Query("SELECT ch FROM CheckList ch WHERE ch.staff.id = :staffId ")
    Set<CheckList> findCheckListsByStaffId(Long staffId);

    @Query("SELECT ch FROM Category c, CheckList ch WHERE c.checkList.staff.store = :storeId and c.name = :name and c.checkList.isComplete = true ")
    Set<CheckList> findCheckListByStoreIdAndCategoryAndIsComplete(Long storeId, String name);

    @Query("SELECT ch FROM Category c, CheckList ch WHERE c.checkList.staff.store = :storeId and c.name = :name and c.checkList.isComplete = false ")
    Set<CheckList> findCheckListByStoreIdAndCategoryAndIsUnComplete(Long storeId, String name);
}
