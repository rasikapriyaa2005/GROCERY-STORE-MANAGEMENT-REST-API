package com.hello.rasikapriya.Repositories;

/*import com.hello.rasikapriya.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<message, Integer> {
}*/




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hello.rasikapriya.Models.GroceryItem;

import java.util.List;

import jakarta.transaction.Transactional;

@Repository
public interface GroceryRepo extends JpaRepository<GroceryItem, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO grocery_item (item_name, category, price, quantity) VALUES (?, ?, ?, ?)", nativeQuery = true)
    void addGroceryItem(String itemName, String category, double price, int quantity);

    @Query("SELECT g FROM GroceryItem g")
    List<GroceryItem> getAllItems();

    @Query("SELECT g FROM GroceryItem g WHERE g.category = ?1")
    List<GroceryItem> findByCategory(String category);
}


