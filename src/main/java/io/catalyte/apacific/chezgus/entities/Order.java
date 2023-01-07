package io.catalyte.apacific.chezgus.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
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

    @NotNull(message = "totalPrice" + REQUIRED_FIELD)
    @ApiModelProperty(notes = "Total price of order in USD",
            required = true)
    private BigDecimal totalPrice;

    @ApiModelProperty(notes = "Order items")
    private List<Item> orderItems;

    public Order() { }

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

    public List<Item> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, orderItems);
    }
}
