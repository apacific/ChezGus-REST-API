package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Item;

import java.util.List;

public interface ItemService {

/**
 * item service interface with create and update methods for an item
 */

    List<Item> getItems(Long orderId);

    Item getItemById(Long orderId, Long id);

    Item addItem(Long orderId, Item item);

    Item updateItemById(Long orderId, Long id, Item item);
}
