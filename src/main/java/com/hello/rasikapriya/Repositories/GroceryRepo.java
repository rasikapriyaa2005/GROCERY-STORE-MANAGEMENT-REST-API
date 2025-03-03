package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.GroceryItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroceryRepo extends JpaRepository<GroceryItem, Integer> {

    @Query("SELECT g FROM GroceryItem g WHERE g.category = ?1")
    List<GroceryItem> findByCategory(String category);

    @Query("SELECT g FROM GroceryItem g ORDER BY g.itemName ASC")
    List<GroceryItem> findAllSortedByName();


    @Query("SELECT g FROM GroceryItem g WHERE g.price < ?1")
    List<GroceryItem> findItemsBelowPrice(double price);

    
    @Query("SELECT g FROM GroceryItem g WHERE g.quantity >= ?1")
    List<GroceryItem> findItemsWithMinQuantity(int minQuantity);


    @Transactional
    @Modifying
    @Query("UPDATE GroceryItem g SET g.price = ?2 WHERE g.itemId = ?1")
    void updateItemPrice(int itemId, double newPrice);

    @Transactional
    @Modifying
    @Query("DELETE FROM GroceryItem g WHERE g.expiryDate < ?1")
    void deleteExpiredItems(String expiryDate);

    List<GroceryItem> findAll(Sort sort);
}
