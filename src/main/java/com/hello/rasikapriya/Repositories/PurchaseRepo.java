package com.hello.rasikapriya.Repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.hello.rasikapriya.Models.PurchaseDetails;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<PurchaseDetails, Integer> {

    List<PurchaseDetails> findByCustomerName(String customerName);

    @Modifying
    @Transactional
    @Query("UPDATE PurchaseDetails p SET p.status = ?2 WHERE p.purchaseId = ?1")
    int updatePurchaseStatus(int purchaseId, String status);

    @Modifying
    @Transactional
    void deleteByCustomerName(String customerName);
}
