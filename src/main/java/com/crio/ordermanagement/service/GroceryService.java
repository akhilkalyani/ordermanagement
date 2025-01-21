package com.crio.ordermanagement.service;

import com.crio.ordermanagement.entity.GroceryItem;
import com.crio.ordermanagement.exception.ResourceNotFoundException;
import com.crio.ordermanagement.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem createGroceryITem(GroceryItem groceryItem){
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> getAllGroceryItems(){
        return groceryItemRepository.findAll();
    }

    public GroceryItem getGroceryById(Long id) throws ResourceNotFoundException{
        return groceryItemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item not found"));
    }

    public GroceryItem updateItem(Long id,GroceryItem groceryItem) throws ResourceNotFoundException{
        GroceryItem item=getGroceryById(id);
        item.setName(groceryItem.getName());
        item.setPrice(groceryItem.getPrice());
        item.setQuantity(groceryItem.getQuantity());
        return groceryItemRepository.save(item);
    }
    public void deleteItem(Long id){
        groceryItemRepository.deleteById(id);
    }
}
