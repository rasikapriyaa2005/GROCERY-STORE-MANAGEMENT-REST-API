package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.GroceryItem;
import com.hello.rasikapriya.Repositories.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroceryService {
    @Autowired
    private GroceryRepo repo;

    
    public List<GroceryItem> addItems(List<GroceryItem> items) {
        return repo.saveAll(items);
    }

    public List<GroceryItem> getSortedItems(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return repo.findAll(sort);
    }

   
    public List<GroceryItem> getPaginatedItems(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findAll(page).getContent();
    }

    
    public List<GroceryItem> getPaginatedSortedItems(int pageNumber, int pageSize, String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        return repo.findAll(page).getContent();
    }

   
    public List<GroceryItem> getItemsBelowPrice(double price) {
        return repo.findItemsBelowPrice(price);
    }

   
    public List<GroceryItem> getItemsWithMinQuantity(int quantity) {
        return repo.findItemsWithMinQuantity(quantity);
    }

    
    public void updateItemPrice(int itemId, double newPrice) {
        repo.updateItemPrice(itemId, newPrice);
    }

    
    public void deleteExpiredItems(String expiryDate) {
        repo.deleteExpiredItems(expiryDate);
    }
}
