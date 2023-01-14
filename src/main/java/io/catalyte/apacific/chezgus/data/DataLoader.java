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

import java.math.BigDecimal;

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
                        1L, 2L, BigDecimal.valueOf(1.00)
                ));
        orderRepository.save(
                new Order(
                        2L, 4L, BigDecimal.valueOf(1.00)
                ));
        orderRepository.save(
                new Order(
                        3L, 1L, BigDecimal.valueOf(1.00)
                ));
    }

    private void loadItems() {
        itemRepository.save(
                new Item(
                        "cheese pizza", "one slice", BigDecimal.valueOf(3.00)
                ));
        itemRepository.save(
                new Item(
                        "toast", "two slices", BigDecimal.valueOf(1.00)
                ));
        itemRepository.save(
                new Item(
                        "fries", "hot, salty, crisp, golden", BigDecimal.valueOf(2.50)
                ));
        itemRepository.save(
                new Item(
                        "eggs", "gently scrambled, with a golden crisp edge", BigDecimal.valueOf(3.25)
                ));
        itemRepository.save(
                new Item(
                        "muffin", "brown sugar + cinnamon", BigDecimal.valueOf(2.50)
                ));
        itemRepository.save(
                new Item(
                        "cookies", "one each - chocolate chip, shortbread, oatmeal", BigDecimal.valueOf(3.75)
                ));
        itemRepository.save(
                new Item(
                        "ice cream", "vanilla, two scoops", BigDecimal.valueOf(2.00)
                ));
        itemRepository.save(
                new Item(
                        "soda", "cola", BigDecimal.valueOf(1.50)
                ));
    }
}