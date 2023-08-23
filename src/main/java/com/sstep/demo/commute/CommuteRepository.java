package com.sstep.demo.commute;

import com.sstep.demo.commute.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommuteRepository extends JpaRepository<Commute, Long> {

    @Query("SELECT c FROM Commute c WHERE c.staff.id = :staffId and c.commuteDate = :nowDate ")
    Commute findByStaffIdAndDate(Long staffId, String nowDate);

    @Query("SELECT c FROM Commute c WHERE c.staff.id = :staffId and c.id = :commuteId")
    Commute findByIdAndStoreId(Long staffId, Long commuteId);

    @Query("SELECT c FROM Commute c WHERE c.staff.id = :staffId")
    Set<Commute> findCommutesByStaffId(Long staffId);

    @Query("SELECT c FROM Commute c WHERE c.staff.store.id = :storeId and c.disputeMessage is NOT null")
    Set<Commute> findDisputeListByStoreIdAndMessageIsNotNull(Long storeId);

    @Query("SELECT c FROM Commute c WHERE c.staff.id = :storeId and c.commuteDate = :commuteDate ")
    Commute findCommuteByStaffIdAndNowDate(Long staffId, String commuteDate);
}
