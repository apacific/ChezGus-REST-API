package io.catalyte.apacific.chezgus.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static io.catalyte.apacific.chezgus.constants.StringConstants.GENERATED_ID;
import static io.catalyte.apacific.chezgus.constants.StringConstants.REQUIRED_FIELD;

/**
 * Order entity.
 * Many-to-many relationship with Item.
 */
@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = GENERATED_ID)
    private Long id;

    @NotNull(message = "order total" + REQUIRED_FIELD)
    @ApiModelProperty(notes = "Total price of order in USD",
            required = true)
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<MenuItem> items = new HashSet<>();

    public CustomerOrder() {
    }

    public CustomerOrder(Long id, Set<MenuItem> items, @NotNull(message = "order total" + REQUIRED_FIELD) BigDecimal totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public Set<MenuItem> getItems() {
        return items;
    }

    public void setItems(Set<MenuItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder customerOrder = (CustomerOrder) o;
        return Objects.equals(id, customerOrder.id) &&
                Objects.equals(items, customerOrder.items) &&
                Objects.equals(totalPrice, customerOrder.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice);
    }
}
