package com.example.demo.mapper;


import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapping{
//    @Mapping(source = "manufacturer.id", target="country_id")
//    @Mapping(source = "manufacturer.name", target = "country_name")
//    @Mapping(source = "manufacturer.code", target="country_code")
    ItemDto toDto(Item item);

//    @Mapping(target= "manufacturer", ignore = true)
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> items);
}
