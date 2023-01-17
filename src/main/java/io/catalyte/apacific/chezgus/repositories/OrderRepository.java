package io.catalyte.apacific.chezgus.repositories;

import io.catalyte.apacific.chezgus.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store customer orders.
 */
@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {

    // Queries
    boolean existsByItemId(Long ItemId);
}