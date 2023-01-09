package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Item;
import io.catalyte.apacific.chezgus.exceptions.BadDataResponse;
import io.catalyte.apacific.chezgus.exceptions.ResourceNotFound;
import io.catalyte.apacific.chezgus.exceptions.ServiceUnavailable;
import io.catalyte.apacific.chezgus.exceptions.UniqueFieldViolation;
import io.catalyte.apacific.chezgus.repositories.ItemRepository;
import io.catalyte.apacific.chezgus.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.catalyte.apacific.chezgus.constants.StringConstants.*;

/**
 * service class that implements ItemService interface
 */
@Service
public class ItemServiceImpl implements ItemService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    /**
     * Gets all items from the database
     *
     * @return a lists of items or an error
     */
    @Override
    public List<Item> getItems() {
        try {
            return itemRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Gets an item by its id
     *
     * @param id the item's id
     * @return an item or an error
     */
    @Override
    public Item getItemById(Long id) {
        Item item;
        try {
            item = itemRepository.findById(id).orElse(null);
            if (item == null) {
                throw new ResourceNotFound(ID_NOT_FOUND);
            }
        } catch (ResourceNotFound | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
        return item;
    }

    /**
     * Add an item to the database
     *
     * @param item the item to be added
     * @return the added item or an error
     */
    @Override
    public Item addItem(Item item) {
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Update an item with an specific id
     *
     * @param id   the item's id
     * @param item the item's new information
     * @return an item
     */
    @Override
    public Item updateItemById(Long id, Item item) {
        Item existingItem;
        //check if id in path matches id in item's new information
        if (!item.getId().equals(id)) {
            throw new BadDataResponse(ID_MUST_MATCH);
        } else if (item.getId() == null) {
            throw new ResourceNotFound(ID_NOT_FOUND);
        }
        try {
            //get existing item from database
            existingItem = itemRepository.findById(id).orElse(null);
            return this.addItem(item);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Deletes orders without encounters from the database
     *
     * @param id the id of the order to be deleted
     */
    @Override
    public void deleteItem(Long id) {
        try {
            // checks if Item exists for matching id
            if (!itemRepository.existsById(id)) {
                // throws error if Item doesn't exist in database
                throw new ResourceNotFound(NOT_FOUND);
            }
            //delete order if there are no items
            itemRepository.deleteById(id);
        } catch (UniqueFieldViolation | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
        //Throws error if order doesn't exist in database
        logger.error(String.valueOf(new ResourceNotFound(ID_NOT_FOUND)));
        throw new ResourceNotFound(ID_NOT_FOUND);
    }
}
