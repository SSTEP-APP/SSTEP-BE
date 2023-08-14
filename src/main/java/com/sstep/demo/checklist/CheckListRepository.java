package com.sstep.demo.checklist;

import com.sstep.demo.checklist.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    @Query("SELECT s.checkLists FROM Staff s WHERE s.id = :staffId ")
    Set<CheckList> findCheckListsByStaffId(Long staffId);

    @Query("SELECT ch FROM CheckList ch WHERE ch.id = :checklistId ")
    CheckList findCheckListById(Long checklistId);

    @Query("SELECT ch FROM Category c, CheckList ch WHERE c.id = :categoryId and ch.id = :id")
    CheckList findCheckListByCategoryIdAndId(Long id, Long categoryId);
}
