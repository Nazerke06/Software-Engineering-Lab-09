package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.services.ItemsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemsServices itemsServices;

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems() {
        List<ItemDto> items = itemsServices.getItemsList();
        return ResponseEntity.ok(items);
    }

    // GET ONE ITEM BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id) {
        ItemDto itemDto = itemsServices.getItemById(id);
        if (itemDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        ItemDto created = itemsServices.addItem(itemDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(
            @PathVariable Long id,
            @RequestBody ItemDto itemDto
    ) {
        ItemDto updated = itemsServices.updateItem(id, itemDto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean deleted = itemsServices.deleteItem(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
