package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.MenuItem;

import java.util.List;

/**
 * Item service interface with create and update methods for an Item
 */
public interface ItemService {
    List<MenuItem> getItems();
    MenuItem getItemById(Long id);
    MenuItem addItem(MenuItem menuItem);
    MenuItem updateItemById(Long id, MenuItem menuItem);
    void deleteItem(Long id);
}
