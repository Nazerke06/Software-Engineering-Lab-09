package com.example.demo.services;

import com.example.demo.dto.ItemDto;

import java.util.List;

public interface ItemsServices {
    List<ItemDto> getItemsList();
    ItemDto getItemById(Long id);
    ItemDto addItem(ItemDto itemDto);
    ItemDto updateItem(Long id, ItemDto itemDto);
    boolean deleteItem(Long id);


}
