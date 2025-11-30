package com.example.demo.mapper;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ItemMapperTest {

    private final ItemMapper itemMapper = new ItemMapperImpl();

    @Test
    void convertEntityToDtoTest() {
        Item item = new Item(1L, "Iphone 16", 600000, 100);
        ItemDto itemDto = itemMapper.toDto(item);

        Assertions.assertNotNull(itemDto);
        Assertions.assertEquals(item.getId(), itemDto.getId());
        Assertions.assertEquals(item.getItemName(), itemDto.getItemName());
        Assertions.assertEquals(item.getItemPrice(), itemDto.getItemPrice());
        Assertions.assertEquals(item.getQuantity(), itemDto.getQuantity());
    }

    @Test
    void convertDtoToEntityTest() {
        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .itemName("Iphone 16")
                .itemPrice(600000)
                .quantity(100)
                .build();

        Item item = itemMapper.toEntity(itemDto);

        Assertions.assertNotNull(item);
        Assertions.assertEquals(itemDto.getId(), item.getId());
        Assertions.assertEquals(itemDto.getItemName(), item.getItemName());
        Assertions.assertEquals(itemDto.getItemPrice(), item.getItemPrice());
        Assertions.assertEquals(itemDto.getQuantity(), item.getQuantity());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Iphone 16", 600000, 100));
        items.add(new Item(2L, "Iphone 16S", 700000, 80));
        items.add(new Item(3L, "Iphone 16 Pro", 900000, 50));
        items.add(new Item(4L, "Iphone 16 Pro Max", 1000000, 20));

        List<ItemDto> itemDtoList = itemMapper.toDtoList(items);

        Assertions.assertNotNull(itemDtoList);
        Assertions.assertEquals(items.size(), itemDtoList.size());

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            ItemDto itemDto = itemDtoList.get(i);

            Assertions.assertEquals(item.getId(), itemDto.getId());
            Assertions.assertEquals(item.getItemName(), itemDto.getItemName());
            Assertions.assertEquals(item.getItemPrice(), itemDto.getItemPrice());
            Assertions.assertEquals(item.getQuantity(), itemDto.getQuantity());
        }
    }
}
