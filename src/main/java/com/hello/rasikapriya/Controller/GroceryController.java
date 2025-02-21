package com.hello.rasikapriya.Controller;

/*import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.rasikapriya.message;
import com.hello.rasikapriya.messageservice;
@RestController
public class Democontroller {
      @Autowired
    messageservice obj;
    @PostMapping("/postdata")
    public ResponseEntity<message> addMessage(@RequestBody message a)
    {
        return new ResponseEntity<>(obj.add(a),HttpStatus.ACCEPTED);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<message>> getAllMessages() {
        return new ResponseEntity<>(obj.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<message> getMessageById(@PathVariable int id) {
        return obj.getMessageById(id)
                  .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<message> updateMessage(@PathVariable int id, @RequestBody message newMessage) {
        try {
            return new ResponseEntity<>(obj.updateMessage(id, newMessage), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        try {
            obj.deleteMessage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }*/
     

     import java.util.List;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.http.ResponseEntity;
     import org.springframework.web.bind.annotation.*;

import com.hello.rasikapriya.Models.GroceryItem;
import com.hello.rasikapriya.Services.GroceryService;
     
     @RestController
     @RequestMapping("/api/grocery")
     public class GroceryController {
         @Autowired
         private GroceryService service;
     
         // Add multiple grocery items
         @PostMapping
         public ResponseEntity<List<GroceryItem>> addGroceryItems(@RequestBody List<GroceryItem> items) {
             List<GroceryItem> savedItems = service.addItems(items);
             return ResponseEntity.status(201).body(savedItems);
         }
     
         // Get all grocery items sorted by a field
         @GetMapping("/sortBy/{field}")
         public List<GroceryItem> getSortedItems(@PathVariable String field) {
             return service.sortItems(field);
         }
     
         // Get paginated grocery items
         @GetMapping("/{pageNumber}/{pageSize}")
         public List<GroceryItem> getPaginatedItems(@PathVariable int pageNumber, @PathVariable int pageSize) {
             return service.getPaginatedItems(pageSize, pageNumber);
         }
     
         // Get paginated and sorted grocery items
         @GetMapping("/{pageNumber}/{pageSize}/{field}")
         public List<GroceryItem> getPaginatedSortedItems(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
             return service.getPaginatedSortedItems(pageSize, pageNumber, field);
         }
     
         // Get items by category
         @GetMapping("/category/{category}")
         public List<GroceryItem> getItemsByCategory(@PathVariable String category) {
             return service.getItemsByCategory(category);
         }
     
         // Delete grocery item by ID
         @DeleteMapping("/{id}")
         public ResponseEntity<String> deleteGroceryItem(@PathVariable int id) {
             return service.deleteGroceryItem(id);
         }
     }
     
