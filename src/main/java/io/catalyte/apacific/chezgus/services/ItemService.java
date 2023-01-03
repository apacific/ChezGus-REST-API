package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Item;

import java.util.List;

public interface ItemService {

/**
 * item service interface with create and update methods for an item
 */

    List<Item> getItems();

    Item getItemById(Long id);

    Item addItem(Item item);

    Item updateItemById(Long id, Item item);
}
