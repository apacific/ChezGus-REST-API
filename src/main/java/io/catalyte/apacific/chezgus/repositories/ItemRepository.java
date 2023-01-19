package io.catalyte.apacific.chezgus.repositories;

import io.catalyte.apacific.chezgus.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository to store menu items.
 */
@Repository
public interface ItemRepository extends JpaRepository<MenuItem, Long> {

    // Queries
    boolean existsById(Long Id);
    List<MenuItem> findByPrice(BigDecimal price);
}