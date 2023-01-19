package io.catalyte.apacific.chezgus.data;


import io.catalyte.apacific.chezgus.entities.LineItem;
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
import java.util.HashSet;
import java.util.Set;

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

    MenuItem item1 = new MenuItem(
            "cheese pizza", "one slice", BigDecimal.valueOf(3.00)
    );
    MenuItem item2 =
            new MenuItem(
                    "toast", "two slices", BigDecimal.valueOf(1.00)
            );
    MenuItem item3 =
            new MenuItem(
                    "eggs", "gently scrambled, with a golden crisp edge", BigDecimal.valueOf(3.25)
            );
    MenuItem item4 =
            new MenuItem(
                    "fries", "hot, salty, crisp", BigDecimal.valueOf(2.50)
            );
    MenuItem item5 =
            new MenuItem(
                    "muffin", "topped with brown sugar + cinnamon", BigDecimal.valueOf(2.50)
            );
    MenuItem item6 =
            new MenuItem(
                    "cookies", "one each - chocolate chip, shortbread, oatmeal", BigDecimal.valueOf(3.75)
            );
    MenuItem item7 =
            new MenuItem(
                    "ice cream", "vanilla, two scoops", BigDecimal.valueOf(2.00)
            );
    MenuItem item8 =
            new MenuItem(
                    "vanilla frappe", "Vincent Vega approves", BigDecimal.valueOf(5.00)
            );
    MenuItem item9 =
            new MenuItem(
                    "soda", "cola", BigDecimal.valueOf(1.50)
            );

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading...");
        loadOrders();
        loadItems();
    }

    private void loadOrders() {


        Set<MenuItem> items1 = new HashSet<>();
        items1.add(item1);
        orderRepository.save(
                new CustomerOrder(
                        1L, items1, items1.iterator().next().getPrice()
                ));
        orderRepository.save(
                new CustomerOrder(
                        2L, null, BigDecimal.valueOf(1.00)
                ));
        orderRepository.save(
                new CustomerOrder(
                        3L, null, BigDecimal.valueOf(1.00)
                ));
    }

    private void loadItems() {

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
        itemRepository.save(item7);
        itemRepository.save(item8);
        itemRepository.save(item9);
    }
}