package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Order;
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
 * Service class that implements OrderService interface
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    /**
     * Gets orders from the database
     *
     * @return a list of orders or an error
     */

    @Override
    public List<Order> getOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Gets an order by id
     *
     * @param id the order's id
     * @return a order or an error
     */
    @Override
    public Order getOrderById(Long id) {
        Order order;
        try {
            order = orderRepository.findById(id).orElse(null);
            if (order == null) {
                throw new ResourceNotFound(ID_NOT_FOUND);
            }
        } catch (ResourceNotFound | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
        return order;
    }


    /**
     * Adds a new order to the database
     *
     * @param order the order being added
     * @return the newly added order, or an error
     */
    @Override
    public Order addOrder(Order order) {

        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceUnavailable(e);
        }
    }

    /**
     * Updates a order with a specific id
     *
     * @param id the id of the order to be updated
     * @param order the order's pending information
     * @return the order's new information or an error
     */
    @Override
    public Order updateOrderById(Long id, Order order) {
        Order existingOrder;

        // Checks if id in path matches order's new information
        if (!order.getId().equals(id)) {
            throw new BadDataResponse(ID_MUST_MATCH);
        }
        try {
            // Gets the existing order from the database
            existingOrder = orderRepository.findById(id).orElse(null);
            // Throws an error if order is null
            if (existingOrder == null) {
                throw new ResourceNotFound(ID_NOT_FOUND);
            }
            return orderRepository.save(order);
        } catch (ResourceNotFound | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
    }
    /**
     * Deletes orders without encounters from the database
     *
     * @param id the id of the order to be deleted
     */
    @Override
    public void deleteOrder(Long id) {
        try {
            // Checks if order exists for that id
            if (orderRepository.existsById(id)) {
                // Deletes order if it exists
                orderRepository.deleteById(id);
                return;
            } else {
                // Throws error if order doesn't exist in database
                logger.error(String.valueOf(new ResourceNotFound(ID_NOT_FOUND)));
                throw new ResourceNotFound(ID_NOT_FOUND);
            }
        } catch (UniqueFieldViolation | ServiceUnavailable e) {
            logger.error(e.getLocalizedMessage());
            throw e;
        }
    }
}
