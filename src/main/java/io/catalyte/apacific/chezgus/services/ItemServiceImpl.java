package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Item;
import io.catalyte.apacific.chezgus.exceptions.BadDataResponse;
import io.catalyte.apacific.chezgus.exceptions.ServiceUnavailable;
import io.catalyte.apacific.chezgus.repositories.ItemRepository;
import io.catalyte.apacific.chezgus.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import static io.catalyte.apacific.chezgus.constants.StringConstants.ORDER_ID_NOT_FOUND;

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
     * Gets items from the database based on a order's id
     *
     * @param orderId the id of the order whose item it is
     * @return a lists of items or an error
     */
    @Override
    public List<Item> getItems(Long orderId) {

        boolean validOrderId;
        Item item = new Item();
        item.setOrderId(orderId);

        //check if order id in item's information is in database
        try {
            validOrderId = orderRepository.existsById(orderId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }

        //throw error if order doesn't exist
        if (!validOrderId) {
            throw new BadDataResponse(ORDER_ID_NOT_FOUND);
        }

        try {
            Example<Item> itemExample = Example.of(item);
            return itemRepository.findAll(itemExample);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Gets an item by its id
     *
     * @param orderId the id of the order whose item it is
     * @param id        the item's id
     * @return an item or an error
     */
    @Override
    public Item getItemById(Long orderId, Long id) {
        boolean validOrderId;
        Item item;

        //check if order id in item's information is in database
        try {
            validOrderId = orderRepository.existsById(orderId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new HttpServerErrorException.ServiceUnavailable(e);
        }

        //throw error if order doesn't exist
        if (!validOrderId) {
            throw new BadDataResponse(ORDER_ID_NOT_FOUND);
        }

        try {
            item = itemRepository.findById(id).orElse(null);
            if (item != null && item.getOrderId().equals(orderId)) {
                return item;
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
        throw new ResourceNotFound(ITEM_ID_NOT_FOUND);
    }

    /**
     * Add an item to the database
     *
     * @param orderId the id of the item's order
     * @param item the item to be added
     * @return the added item or an error
     */
    @Override
    public Item addItem(Long orderId, Item item) {

        boolean validOrderId;
        boolean sameOrderId;

        sameOrderId = orderId.equals(item.getOrderId());

        //throw error if order id passed in does not equal order id in item's information
        if (!sameOrderId) {
            throw new BadDataResponse(ORDER_ID_MUST_MATCH);
        }

        //check if order id in item's information is in database
        try {
            validOrderId = orderRepository.existsById(item.getOrderId());
        } catch (Exception e) {
            throw new ServiceUnavailable(e);
        }

        //throw error if order doesn't exist
        if (!validOrderId) {
            throw new BadDataResponse(ORDER_ID_NOT_FOUND);
        }

        //check if total cost and copay have exactly 2 decimal places
        if (item.getCopay().scale() != 2 || item.getTotalCost().scale() != 2) {
            throw new BadDataResponse(BAD_REQUEST_DECIMAL);
        }

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
     * @param orderId the id of the item's order
     * @param id        the item's id
     * @param item the item's new information
     * @return an item
     */
    @Override
    public Item updateItemById(Long orderId, Long id, Item item) {

        Item existingItem;

        //check if id in path matches id in item's new information
        if (!item.getId().equals(id)) {
            throw new BadDataResponse(ID_MUST_MATCH);
        }

        try {
            //get existing item from database
            existingItem = itemRepository.findById(id).orElse(null);

            //throw error if item is null
            if (existingItem != null && existingItem.getOrderId().equals(orderId)) {

                return this.addItem(orderId, item);
            }


        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
        throw new ResourceNotFound(ID_NOT_FOUND);
    }
}

