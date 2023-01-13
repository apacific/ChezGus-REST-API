package io.catalyte.apacific.chezgus.data;


import io.catalyte.apacific.chezgus.entities.Item;
import io.catalyte.apacific.chezgus.entities.Order;
import io.catalyte.apacific.chezgus.repositories.ItemRepository;
import io.catalyte.apacific.chezgus.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runs after the server starts and executes methods that load initial datasets into the database
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    private Order order1;
    private Order order2;
    private Order order3;
    private Order order4;
    private Order order5;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading...");
        loadOrders();
        loadItems();
    }

    private void loadOrders() {
        orderRepository.save(
                new Order(

                ));
    }

    private void loadItems() {
        itemRepository.save(
                new Item(

                ));
    }
}