package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.MenuItem;
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
    public List<MenuItem> getItems() {
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
    public MenuItem getItemById(Long id) {
        MenuItem menuItem;
        try {
            menuItem = itemRepository.findById(id).orElse(null);
            if (menuItem == null) {
                throw new ResourceNotFound(ID_NOT_FOUND);
            }
        } catch (ResourceNotFound | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
        return menuItem;
    }

    /**
     * Add an item to the database
     *
     * @param menuItem the item to be added
     * @return the added item or an error
     */
    @Override
    public MenuItem addItem(MenuItem menuItem) {
        try {
            return itemRepository.save(menuItem);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Update an item with an specific id
     *
     * @param id   the item's id
     * @param menuItem the item's new information
     * @return an item
     */
    @Override
    public MenuItem updateItemById(Long id, MenuItem menuItem) {
        MenuItem existingMenuItem;
        //check if id in path matches id in item's new information
        if (!menuItem.getId().equals(id)) {
            throw new BadDataResponse(ID_MUST_MATCH);
        } else if (menuItem.getId() == null) {
            throw new ResourceNotFound(ID_NOT_FOUND);
        }
        try {
            //get existing item from database
            existingMenuItem = itemRepository.findById(id).orElse(null);
            return this.addItem(menuItem);
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
