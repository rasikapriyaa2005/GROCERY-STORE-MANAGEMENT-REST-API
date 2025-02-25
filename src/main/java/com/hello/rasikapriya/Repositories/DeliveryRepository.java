package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.Delivery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // ✅ Find deliveries by user ID
    List<Delivery> findByUserId(Long userId);

    // ✅ JPQL Query: Find deliveries sorted by a field
    @Query("SELECT d FROM Delivery d ORDER BY " +
           "CASE WHEN :order = 'asc' THEN d.deliveryDate END ASC, " +
           "CASE WHEN :order = 'desc' THEN d.deliveryDate END DESC")
    List<Delivery> findAllSorted(String order, Sort sort);
}
