package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.Delivery;
import com.hello.rasikapriya.Repositories.DeliveryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    // ✅ Add multiple deliveries
    public void addDeliveries(List<Delivery> deliveries) {
        deliveryRepository.saveAll(deliveries);
    }

    // ✅ Get all deliveries
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    // ✅ Get a delivery by ID
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    // ✅ Get deliveries by user ID
    public List<Delivery> getDeliveriesByUserId(Long userId) {
        return deliveryRepository.findByUserId(userId);
    }

    // ✅ Sorting by a specific field (ASC/DESC)
    public List<Delivery> getSortedDeliveries(String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return deliveryRepository.findAll(sort);
    }

    // ✅ Pagination support
    public Page<Delivery> getDeliveriesWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return deliveryRepository.findAll(pageable);
    }

    // ✅ Pagination with Sorting
    public Page<Delivery> getDeliveriesWithPaginationAndSorting(int pageNumber, int pageSize, String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return deliveryRepository.findAll(pageable);
    }

    // ✅ Update a delivery
    public Delivery updateDelivery(Long id, Delivery delivery) {
        return deliveryRepository.findById(id)
                .map(existingDelivery -> {
                    existingDelivery.setUserId(delivery.getUserId());
                    existingDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
                    existingDelivery.setDeliveryDate(delivery.getDeliveryDate());
                    existingDelivery.setAddress(delivery.getAddress());
                    existingDelivery.setOrderIds(delivery.getOrderIds());
                    return deliveryRepository.save(existingDelivery);
                })
                .orElse(null);
    }

    // ✅ Delete a delivery
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
