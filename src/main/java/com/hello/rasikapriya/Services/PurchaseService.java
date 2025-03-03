package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.PurchaseDetails;
import com.hello.rasikapriya.Repositories.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepo repo;

    public List<PurchaseDetails> addMultiplePurchases(List<PurchaseDetails> ref) {
        return repo.saveAll(ref);
    }

    public List<PurchaseDetails> getSortedPurchases(String field) {
        return repo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public List<PurchaseDetails> getPaginatedPurchases(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return repo.findAll(pageable).getContent();
    }

    public List<PurchaseDetails> getPaginatedSortedPurchases(int pageNumber, int pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, field));
        return repo.findAll(pageable).getContent();
    }

    public boolean updatePurchaseStatus(int purchaseId, String status) {
        int updated = repo.updatePurchaseStatus(purchaseId, status);
        return updated > 0;
    }

    public ResponseEntity<String> deletePurchase(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Purchase deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Purchase not found!");
        }
    }
}
