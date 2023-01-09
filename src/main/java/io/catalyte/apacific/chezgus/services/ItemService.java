package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Item;

import java.util.List;

/**
 * Item service interface with create and update methods for an Item
 */
public interface ItemService {
    List<Item> getItems();
    Item getItemById(Long id);
    Item addItem(Item item);
    Item updateItemById(Long id, Item item);
    void deleteItem(Long id);
}
