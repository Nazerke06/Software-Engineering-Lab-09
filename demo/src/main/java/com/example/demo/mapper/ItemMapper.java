package com.example.demo.mapper;


import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "itemName", target = "itemName")
    @Mapping(source = "itemPrice", target = "itemPrice")
    ItemDto toDto(Item item);

    @Mapping(source = "itemName", target = "itemName")
    @Mapping(source = "itemPrice", target = "itemPrice")
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> items);
}
