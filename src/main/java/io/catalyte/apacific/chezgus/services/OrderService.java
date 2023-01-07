package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.Order;
import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order getOrderById(Long id);

    Order addOrder(Order order);

    Order updateOrderById(Long id, Order order);

    void deleteOrder(Long id);
}
