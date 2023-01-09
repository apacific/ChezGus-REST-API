package io.catalyte.apacific.chezgus.repositories;


import io.catalyte.apacific.chezgus.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store menu items.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Queries
    boolean existsById(Long Id);
}