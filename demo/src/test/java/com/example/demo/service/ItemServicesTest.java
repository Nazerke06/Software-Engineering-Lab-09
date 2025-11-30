package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.services.ItemsServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest

public class ItemServicesTest {

    @Autowired
    private ItemsServices itemsServices;


    void getItemsServices() {
        List <ItemDto> itemDtos = itemsServices.getItemsList();

        Assertions.assertNotNull(itemDtos);

        Assertions.assertNotEquals(0, itemDtos.size());

        for (ItemDto itemDto : itemDtos) {
            Assertions.assertNotNull(itemDto);
            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getItemName());
            Assertions.assertNotNull(itemDto.getItemPrice());
            Assertions.assertNotNull(itemDto.getQuantity());

        }
    }
    @Test
    void getItemsByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemsServices.getItemsList().size());

        Long someItemId = itemsServices.getItemsList().get(randomIndex).getId();

        // Выполняем поиск товара по идентификатору
        ItemDto itemDto = itemsServices.getItemById(someItemId);

        // Товар не должен быть равен null
        Assertions.assertNotNull(itemDto);

        // Параметры товара не должны быть равны null
        Assertions.assertNotNull(itemDto.getId());
        Assertions.assertNotNull(itemDto.getItemName());
        Assertions.assertNotNull(itemDto.getItemPrice());
        Assertions.assertNotNull(itemDto.getQuantity());



        // Также, если метод сервиса не нашел товар по идентификатору, он должен возвращать null
        // Выполним проверку, передав идентификатор явно не существующего товара
        ItemDto mockItem = itemsServices.getItemById(-1L);
        Assertions.assertNull(mockItem);
    }


    // Проверяем функционал добавления товара
    @Test
    void createItemTest() {
        // Создаем ДТО
        ItemDto itemDto = ItemDto
                .builder()
                .itemName("IPhone 16 Plus")
                .itemPrice(800000)
                .quantity(60)
                .build();

        // Добавляем товар
        ItemDto createdItem = itemsServices.addItem(itemDto);

        // Проверяем, правильно ли сервис добавил товар
        // Добавленный товар не должен быть равен null
        Assertions.assertNotNull(createdItem);

        // Добавленному товару должен был быть присвоен идентификатор
        Assertions.assertNotNull(createdItem.getId());
        Assertions.assertNotEquals(0L, createdItem.getId());

        // Параметры добавленного товара не должны быть равны null
        Assertions.assertNotNull(createdItem.getItemName());
        Assertions.assertNotNull(createdItem.getItemPrice());
        Assertions.assertNotNull(createdItem.getQuantity());

        // Значения параметров добавленного товара должны соответствовать значениям, которые были переданы в ДТО
        Assertions.assertEquals(createdItem.getItemName(), itemDto.getItemName());
        Assertions.assertEquals(createdItem.getItemPrice(), itemDto.getItemPrice());
        Assertions.assertEquals(createdItem.getQuantity(), itemDto.getQuantity());


        // Проверяем, точно ли товар был добавлен (он должен быть сохранен в тестовой базе данных)
        // Загружаем товару по идентификатору, который был ему присвоен
        ItemDto checkItem = itemsServices.getItemById(createdItem.getId());

        // Добавленный товар и его параметры не должны быть null
        Assertions.assertNotNull(checkItem);
        Assertions.assertNotNull(checkItem.getId());
        Assertions.assertNotNull(checkItem.getItemName());
        Assertions.assertNotNull(checkItem.getItemPrice());
        Assertions.assertNotNull(checkItem.getQuantity());

        // Проверяем значения параметров товара
        Assertions.assertEquals(checkItem.getId(), createdItem.getId());
        Assertions.assertEquals(checkItem.getItemName(), createdItem.getItemName());
        Assertions.assertEquals(checkItem.getItemPrice(), createdItem.getItemPrice());
        Assertions.assertEquals(checkItem.getQuantity(), createdItem.getQuantity());
    }


    // Проверяем функционал обновления товара
    @Test
    void updateItemTest() {
        // Генерируем рандомный идентификатор товара
        Random random = new Random();
        int randomIndex = random.nextInt(itemsServices.getItemsList().size());

        // Получаем идентификатор рандомного товара, который содержится в базе данных
        Long someItemId = itemsServices.getItemsList().get(randomIndex).getId();

        // Подготавливаем отредактированные данные товара
        ItemDto itemDto = ItemDto
                .builder()
                .id(someItemId)
                .itemName("New Item Name")
                .itemPrice(10000)
                .quantity(30)
                .build();

        // Выполняем обновление товара
        ItemDto updatedItem = itemsServices.updateItem(itemDto.getId(), itemDto);

        // Проверяем, правильно ли сервис обновил данные товара
        // Обновленный товар не должен быть равен null
        Assertions.assertNotNull(updatedItem);

        // Параметры обновленного товара не должны быть равны null
        Assertions.assertNotNull(updatedItem.getId());
        Assertions.assertNotNull(updatedItem.getItemName());
        Assertions.assertNotNull(updatedItem.getItemPrice());
        Assertions.assertNotNull(updatedItem.getQuantity());

        // Значения параметров обновленного товара должны соответствовать значениям, которые были переданы в ДТО, особенно идентификаторы
        Assertions.assertEquals(updatedItem.getId(), itemDto.getId());
        Assertions.assertEquals(updatedItem.getItemName(), itemDto.getItemName());
        Assertions.assertEquals(updatedItem.getItemPrice(), itemDto.getItemPrice());
        Assertions.assertEquals(updatedItem.getQuantity(), itemDto.getQuantity());


        // Проверяем, точно ли данные товара были обновлены, сохранены ли они в тестовой базе данных
        // Загружаем товар по идентификатору
        ItemDto checkItem = itemsServices.getItemById(updatedItem.getId());

        // Обновленный товар и его параметры не должны быть null
        Assertions.assertNotNull(checkItem);
        Assertions.assertNotNull(checkItem.getId());
        Assertions.assertNotNull(checkItem.getItemName());
        Assertions.assertNotNull(checkItem.getItemPrice());
        Assertions.assertNotNull(checkItem.getQuantity());

        // Проверяем значения параметров товара. Точно ли они отредактированы в тестовой базе данных?
        Assertions.assertEquals(checkItem.getId(), updatedItem.getId());
        Assertions.assertEquals(checkItem.getItemName(), updatedItem.getItemName());
        Assertions.assertEquals(checkItem.getItemPrice(), updatedItem.getItemPrice());
        Assertions.assertEquals(checkItem.getQuantity(), updatedItem.getQuantity());



        // Также, если метод сервиса не нашел товар по идентификатору, он должен возвращать null
        // Выполним проверку, передав идентификатор явно не существующего товара
        ItemDto mockItem = itemsServices.updateItem(-1L, itemDto);
        Assertions.assertNull(mockItem);
    }

    @Test
    void deleteItemTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemsServices.getItemsList().size());

        Long someItemId = itemsServices.getItemsList().get(randomIndex).getId();

        boolean deleted = itemsServices.deleteItem(someItemId);
        Assertions.assertTrue(deleted);

        ItemDto itemDto = itemsServices.getItemById(someItemId);
        Assertions.assertNull(itemDto);



        boolean mockDeleted = itemsServices.deleteItem(someItemId);
        Assertions.assertFalse(mockDeleted);
    }
}
