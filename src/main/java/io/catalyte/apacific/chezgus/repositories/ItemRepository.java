package io.catalyte.apacific.chezgus.repositories;


import io.catalyte.apacific.chezgus.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store menu items.
 */
@Repository
public interface ItemRepository extends JpaRepository<MenuItem, Long> {

    // Queries
    boolean existsById(Long Id);
}