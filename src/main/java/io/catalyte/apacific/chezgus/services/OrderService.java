package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Order;
import java.util.List;

/**
 * Order service interface with create and update methods for an Order
 */
public interface OrderService {
    List<Order> getOrders();
    Order getOrderById(Long id);
    Order addOrder(Order order);
    Order updateOrderById(Long id, Order order);
    void deleteOrder(Long id);
}
