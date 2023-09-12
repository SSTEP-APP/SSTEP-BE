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

    @Query("SELECT ch FROM CheckListManager cm WHERE cm.checkList.staff.id = :staffId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = true ")
    Set<CheckList> findCheckListByStaffIdAndCategoryIdAndIsCompleteAndDate(Long staffId, Long categoryId, String date);

    @Query("SELECT ch FROM CheckListManager cm WHERE cm.checkList.staff.id = :staffId and cm.checkList.category.id = :categoryId and cm.checkList.date = :date and cm.checkList.isComplete = false ")
    Set<CheckList> findCheckListByStaffIdAndCategoryIdAndIsUnCompleteAndDate(Long staffId, Long categoryId, String date);

    @Query("SELECT ch FROM CheckList ch WHERE ch.category.id = :id ")
    Set<CheckList> findCheckListsByCategoryId(long id);
}
