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

    @Query("SELECT cm.checkList FROM CheckListManager cm WHERE cm.staff.id = :staffId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = true ")
    Set<CheckList> findCheckListByStaffIdAndCategoryIdAndIsCompleteAndDate(Long staffId, Long categoryId, String date);

    @Query("SELECT cm.checkList FROM CheckListManager cm WHERE cm.staff.id = :staffId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = false ")
    Set<CheckList> findCheckListByStaffIdAndCategoryIdAndIsUnCompleteAndDate(Long staffId, Long categoryId, String date);

    @Query("SELECT ch FROM CheckList ch WHERE ch.category.id = :id ")
    Set<CheckList> findCheckListsByCategoryId(long id);

    @Query("SELECT ch FROM CheckList ch WHERE ch.staff.store.id = :storeId and ch.isComplete = false ")
    Set<CheckList> findCheckListsByStoreIdAndIsUnComplete(Long storeId);

    @Query("SELECT cm.checkList FROM CheckListManager cm WHERE cm.staff.store.id = :storeId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = false ")
    Set<CheckList> findCheckListByStoreIdAndCategoryIdAndIsUnCompleteAndDate(Long storeId, Long categoryId, String date);

    @Query("SELECT cm.checkList FROM CheckListManager cm WHERE cm.staff.store.id = :storeId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = true ")
    Set<CheckList> findCheckListByStoreIdAndCategoryIdAndIsCompleteAndDate(Long storeId, Long categoryId, String date);

    @Query("SELECT cm.checkList FROM CheckListManager cm WHERE cm.staff.id = :staffId and cm.checkList.isComplete = false")
    Set<CheckList> findCheckListsByStaffIdAndIsUnCompleted(Long staffId);
}
