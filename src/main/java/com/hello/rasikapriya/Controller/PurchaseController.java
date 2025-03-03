package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.PurchaseDetails;
import com.hello.rasikapriya.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ref")
public class PurchaseController {
    @Autowired
    private PurchaseService service;

    @PostMapping
    public ResponseEntity<List<PurchaseDetails>> createPurchase(@RequestBody List<PurchaseDetails> purchaseDetails) {
        List<PurchaseDetails> savedPurchases = service.addMultiplePurchases(purchaseDetails);
        return ResponseEntity.ok(savedPurchases);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<PurchaseDetails>> getSortedPurchases(@PathVariable String field) {
        return ResponseEntity.ok(service.getSortedPurchases(field));
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<List<PurchaseDetails>> getPaginatedPurchases(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(service.getPaginatedPurchases(pageNumber, pageSize));
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}/sort/{field}")
    public ResponseEntity<List<PurchaseDetails>> getPaginatedSortedPurchases(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return ResponseEntity.ok(service.getPaginatedSortedPurchases(pageNumber, pageSize, field));
    }

    @PutMapping("/{purchaseId}/status/{status}")
    public ResponseEntity<String> updatePurchaseStatus(@PathVariable int purchaseId, @PathVariable String status) {
        boolean updated = service.updatePurchaseStatus(purchaseId, status);
        if (updated) {
            return ResponseEntity.ok("Purchase status updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Purchase not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable int id) {
        return service.deletePurchase(id);
    }
}
