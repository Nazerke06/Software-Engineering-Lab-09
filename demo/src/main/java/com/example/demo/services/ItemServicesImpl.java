package com.example.demo.services;


import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.mapper.ItemMapping;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServicesImpl implements ItemsServices {

    private final ItemRepository itemRepository;
    private final CountryRepository countryRepository;
    private final ItemMapping itemMapping;

    @Override
    public List<ItemDto> getItemsList() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> dtoList = itemMapping.toDtoList(items);
        return dtoList;
    }

    @Override
    public ItemDto getItemById(Long id) {
        Item item= itemRepository.findById(id).orElse(null);
        if(item == null) return null;
        ItemDto dto = itemMapping.toDto(item);
        return dto;
    }

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = itemMapping.toEntity(itemDto);
        Item createdItem = itemRepository.save(item);
        ItemDto createdItemDto = itemMapping.toDto(createdItem);
        return createdItemDto;
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        ItemDto checkItemDto = getItemById(id);
        if(checkItemDto == null) return null;

        Item item=itemMapping.toEntity(checkItemDto);
        Item updatedItem = itemRepository.save(item);
        ItemDto updatedItemDto = itemMapping.toDto(updatedItem);
        return updatedItemDto;
    }

    @Override
    public boolean deleteItem(Long id) {
        Item deletedItem = itemRepository.findById(id).orElse(null);
        if(deletedItem == null) return false;
        itemRepository.delete(deletedItem);
        return true;
    }
}
