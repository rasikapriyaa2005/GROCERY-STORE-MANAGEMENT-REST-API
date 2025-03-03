package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.GroceryItem;
import com.hello.rasikapriya.Services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {
    @Autowired
    private GroceryService service;


    @PostMapping
    public ResponseEntity<List<GroceryItem>> addGroceryItems(@RequestBody List<GroceryItem> items) {
        return ResponseEntity.status(201).body(service.addItems(items));
    }

    @GetMapping("/sortBy/{field}/{direction}")
    public List<GroceryItem> getSortedItems(@PathVariable String field, @PathVariable String direction) {
        return service.getSortedItems(field, direction);
    }

   
    @GetMapping("/paginated")
    public List<GroceryItem> getPaginatedItems(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return service.getPaginatedItems(pageNumber, pageSize);
    }

    @GetMapping("/paginated/sorted")
    public List<GroceryItem> getPaginatedSortedItems(@RequestParam int pageNumber, @RequestParam int pageSize,
                                                     @RequestParam String field, @RequestParam String direction) {
        return service.getPaginatedSortedItems(pageNumber, pageSize, field, direction);
    }

   
    @GetMapping("/below-price/{price}")
    public List<GroceryItem> getItemsBelowPrice(@PathVariable double price) {
        return service.getItemsBelowPrice(price);
    }

   
    @PutMapping("/update-price/{id}/{price}")
    public void updateItemPrice(@PathVariable int id, @PathVariable double price) {
        service.updateItemPrice(id, price);
    }

    @DeleteMapping("/delete-expired/{expiryDate}")
    public void deleteExpiredItems(@PathVariable String expiryDate) {
        service.deleteExpiredItems(expiryDate);
    }
}
