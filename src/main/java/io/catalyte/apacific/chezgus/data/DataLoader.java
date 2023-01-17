package io.catalyte.apacific.chezgus.data;


import io.catalyte.apacific.chezgus.entities.MenuItem;
import io.catalyte.apacific.chezgus.entities.CustomerOrder;
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

    private CustomerOrder customerOrder1;
    private CustomerOrder customerOrder2;
    private CustomerOrder customerOrder3;
    private CustomerOrder customerOrder4;
    private CustomerOrder customerOrder5;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading...");
        loadOrders();
        loadItems();
    }

    private void loadOrders() {
        orderRepository.save(
                new CustomerOrder(
                        1L, 2L, BigDecimal.valueOf(1.00)
                ));
        orderRepository.save(
                new CustomerOrder(
                        2L, 4L, BigDecimal.valueOf(1.00)
                ));
        orderRepository.save(
                new CustomerOrder(
                        3L, 1L, BigDecimal.valueOf(1.00)
                ));
    }

    private void loadItems() {
        itemRepository.save(
                new MenuItem(
                        "cheese pizza", "one slice", BigDecimal.valueOf(3.00)
                ));
        itemRepository.save(
                new MenuItem(
                        "toast", "two slices", BigDecimal.valueOf(1.00)
                ));
        itemRepository.save(
                new MenuItem(
                        "fries", "hot, salty, crisp, golden", BigDecimal.valueOf(2.50)
                ));
        itemRepository.save(
                new MenuItem(
                        "eggs", "gently scrambled, with a golden crisp edge", BigDecimal.valueOf(3.25)
                ));
        itemRepository.save(
                new MenuItem(
                        "muffin", "brown sugar + cinnamon", BigDecimal.valueOf(2.50)
                ));
        itemRepository.save(
                new MenuItem(
                        "cookies", "one each - chocolate chip, shortbread, oatmeal", BigDecimal.valueOf(3.75)
                ));
        itemRepository.save(
                new MenuItem(
                        "ice cream", "vanilla, two scoops", BigDecimal.valueOf(2.00)
                ));
        itemRepository.save(
                new MenuItem(
                        "vanilla frappe", "Vincent Vega approves", BigDecimal.valueOf(5.00)
                ));
        itemRepository.save(
                new MenuItem(
                        "soda", "cola", BigDecimal.valueOf(1.50)
                ));
    }
}