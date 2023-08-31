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

    @Query("SELECT ch FROM CheckList ch, Category c WHERE c.checkList.staff.store.id = :storeId and c.name = :categoryName and c.checkList.date = :date and c.checkList.isComplete = true ")
    Set<CheckList> findCheckListByStoreIdAndCategoryAndIsCompleteAndDate(Long storeId, String categoryName, String date);

    @Query("SELECT ch FROM CheckList ch, Category c WHERE c.checkList.staff.store.id = :storeId and c.name = :categoryName and c.checkList.date = :date and c.checkList.isComplete = false ")
    Set<CheckList> findCheckListByStoreIdAndCategoryAndIsUnCompleteAndDate(Long storeId, String categoryName, String date);
}
