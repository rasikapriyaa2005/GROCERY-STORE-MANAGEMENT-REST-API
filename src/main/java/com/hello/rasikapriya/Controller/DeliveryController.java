package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.Delivery;
import com.hello.rasikapriya.Services.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // ✅ Accepts multiple deliveries in a single request
    @PostMapping
    public ResponseEntity<String> addDeliveries(@RequestBody List<Delivery> deliveries) {
        deliveryService.addDeliveries(deliveries);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deliveries added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(id);
        return delivery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Delivery>> getDeliveriesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(deliveryService.getDeliveriesByUserId(userId));
    }

    // ✅ Sorting by field (ASC/DESC)
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Delivery>> getSortedDeliveries(@PathVariable String field, @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(deliveryService.getSortedDeliveries(field, order));
    }

    // ✅ Pagination support
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<Delivery>> getDeliveriesWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(deliveryService.getDeliveriesWithPagination(pageNumber, pageSize));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        Delivery updatedDelivery = deliveryService.updateDelivery(id, delivery);
        return updatedDelivery != null ? ResponseEntity.ok(updatedDelivery) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok("Delivery deleted successfully");
    }
}
