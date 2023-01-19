package io.catalyte.apacific.chezgus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static io.catalyte.apacific.chezgus.constants.StringConstants.INVALID_POSITIVE;
import static io.catalyte.apacific.chezgus.constants.StringConstants.REQUIRED_FIELD;

@Entity
@Table(name = "line_item")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "menu item ID" + REQUIRED_FIELD)
    private Long menuItemId;
    @NotNull(message = "quantity" + INVALID_POSITIVE)
    private Long quantity;

    public LineItem(@NotNull(message = "menu item ID" + REQUIRED_FIELD) Long menuItemId, @NotNull(message = "quantity" + INVALID_POSITIVE) Long quantity, CustomerOrder CustomerOrder) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.CustomerOrder = CustomerOrder;
    }

    public CustomerOrder getCustomerOrder() {
        return CustomerOrder;
    }

    public void setCustomerOrder(CustomerOrder CustomerOrder) {
        this.CustomerOrder = CustomerOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem item = (LineItem) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(menuItemId, item.menuItemId) &&
                Objects.equals(quantity, item.quantity) &&
                Objects.equals(CustomerOrder, item.CustomerOrder);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", menuItemId=" + menuItemId +
                ", quantity=" + quantity +
                ", CustomerOrder=" + CustomerOrder +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuItemId, quantity, CustomerOrder);
    }

    public LineItem() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private CustomerOrder CustomerOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return menuItemId;
    }

    public void setProductId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}