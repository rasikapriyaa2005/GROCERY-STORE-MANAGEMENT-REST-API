package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.GroceryItem;
import com.hello.rasikapriya.Repositories.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepo repo;

    // Add multiple grocery items to the database
    public List<GroceryItem> addItems(List<GroceryItem> items) {
        return repo.saveAll(items);
    }

    // Get all grocery items sorted by a specific field and direction
    public List<GroceryItem> getSortedItems(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return repo.findAll(sort);
    }

    // Get all grocery items with pagination
    public List<GroceryItem> getPaginatedItems(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findAll(page).getContent();
    }

    // Get all grocery items with pagination and sorting
    public List<GroceryItem> getPaginatedSortedItems(int pageNumber, int pageSize, String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        return repo.findAll(page).getContent();
    }

    // Get grocery items that are below a specific price
    public List<GroceryItem> getItemsBelowPrice(double price) {
        return repo.findItemsBelowPrice(price);
    }

    // Get grocery items that have a minimum quantity
    public List<GroceryItem> getItemsWithMinQuantity(int quantity) {
        return repo.findItemsWithMinQuantity(quantity);
    }

    // Update the price of a grocery item by its ID
    public void updateItemPrice(int itemId, double newPrice) {
        repo.updateItemPrice(itemId, newPrice);
    }

    // Delete expired grocery items by expiry date
    public void deleteExpiredItems(String expiryDate) {
        repo.deleteExpiredItems(expiryDate);
    }
}
