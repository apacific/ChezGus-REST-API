package io.catalyte.apacific.chezgus.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

import static io.catalyte.apacific.chezgus.constants.StringConstants.GENERATED_ID;
import static io.catalyte.apacific.chezgus.constants.StringConstants.REQUIRED_FIELD;

/**
 * Order entity.
 * Many-to-many relationship with Item.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = GENERATED_ID)
    private Long id;

    private Long itemId;

    @NotNull(message = "totalPrice" + REQUIRED_FIELD)
    @ApiModelProperty(notes = "Total price of order in USD",
            required = true)
    private BigDecimal totalPrice;

    public Order() {
    }

    public Order(Long id, Long itemId, @NotNull(message = "totalPrice" + REQUIRED_FIELD) BigDecimal totalPrice) {
        this.id = id;
        this.itemId = itemId;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
                "itemId=" + itemId +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(itemId, order.itemId) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, totalPrice);
    }
}
