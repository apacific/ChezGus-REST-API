package io.catalyte.apacific.chezgus.services;

import io.catalyte.apacific.chezgus.entities.CustomerOrder;
import java.util.List;

/**
 * Order service interface with create and update methods for an Order
 */
public interface OrderService {
    List<CustomerOrder> getOrders();
    CustomerOrder getOrderById(Long id);
    CustomerOrder addOrder(CustomerOrder customerOrder);
    CustomerOrder updateOrderById(Long id, CustomerOrder customerOrder);
    void deleteOrder(Long id);
}
