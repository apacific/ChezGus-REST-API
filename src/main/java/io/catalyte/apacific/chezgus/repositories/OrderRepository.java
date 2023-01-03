package io.catalyte.apacific.chezgus.repositories;

import io.catalyte.apacific.chezgus.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * patient repo which stores patients and has a derived method called existsByEmail
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //Queries
    boolean existsByItemId(Long ItemId);


}