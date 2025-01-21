package com.crio.ordermanagement.controller;

import com.crio.ordermanagement.entity.GroceryItem;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {
    @Autowired
    private GroceryService groceryService;

    @PostMapping("/addItem")
    public ResponseEntity<GroceryItem> createItem(@RequestBody GroceryItem newItem){
        return ResponseEntity.status(HttpStatus.CREATED).body(groceryService.createGroceryITem(newItem));
    }
    @GetMapping("/getItems")
    public List<GroceryItem> getItems(){
        return groceryService.getAllGroceryItems();
    }

    @GetMapping("/getItem/{id}")
    public ResponseEntity<GroceryItem> getItemById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(groceryService.getGroceryById(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/updateItem/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id,@RequestBody GroceryItem updatedItem){
        try {
            return ResponseEntity.ok(groceryService.updateItem(id, updatedItem));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        }
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
