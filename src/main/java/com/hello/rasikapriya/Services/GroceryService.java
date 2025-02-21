package com.hello.rasikapriya.Services;

/*import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.rasikapriya.message;
import com.hello.rasikapriya.MessageRepository;
@Service
public class messageservice {
 @Autowired
    MessageRepository obj;
    public message add(message a)
    {
        return obj.save(a);
    }
    public List<message> getAllMessages()
    {
        return obj.findAll();
    }
    public Optional<message> getMessageById(int id)
    {
        return obj.findById(id);

    }
    public message updateMessage(int id, message newMessage) 
    {
        return obj.findById(id).map(existingMessage -> {
            existingMessage.setId(newMessage.getId()); 
            existingMessage.setFirstname(newMessage.getFirstname());  
            return obj.save(existingMessage);
        }).orElseThrow(() -> new RuntimeException("Message not found with id " + id));
    }    
    public void deleteMessage(int id)
    {
        if(obj.existsById(id))
        {
            obj.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Message not found with id "+id);
        }
    }
}*/


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.rasikapriya.Models.GroceryItem;
import com.hello.rasikapriya.Repositories.GroceryRepo;

@Service
public class GroceryService {
    @Autowired
    private GroceryRepo repo;

    // Add a new grocery item
    public GroceryItem addItem(GroceryItem item) {
        return repo.save(item);
    }

    // Add multiple grocery items
    public List<GroceryItem> addItems(List<GroceryItem> items) {
        return repo.saveAll(items);
    }

    // Get all items sorted by a specific field
    public List<GroceryItem> sortItems(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return repo.findAll(sort);
    }

    // Get items with pagination
    public List<GroceryItem> getPaginatedItems(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findAll(page).getContent();
    }

    // Get items with pagination and sorting
    public List<GroceryItem> getPaginatedSortedItems(int pageSize, int pageNumber, String field) {
        return repo.findAll(PageRequest.of(pageNumber, pageSize)
                .withSort(Sort.by(Sort.Direction.ASC, field))).getContent();
    }

    // Get items by category
    public List<GroceryItem> getItemsByCategory(String category) {
        return repo.findByCategory(category);
    }

    // Delete grocery item by ID
    public ResponseEntity<String> deleteGroceryItem(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Item deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
        }
    }
}
