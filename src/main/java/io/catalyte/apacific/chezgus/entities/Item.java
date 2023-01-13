package io.catalyte.apacific.chezgus.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static io.catalyte.apacific.chezgus.constants.StringConstants.*;

/**
 * Item entity and associated attributes.
 * Many-to-many relationship with Order.
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = GENERATED_ID)
    private Long id;

    @ApiModelProperty(notes = ITEM_NAME)
    private String name;

    @ApiModelProperty(notes = ITEM_DESCRIPTION)
    private String description;

    @NotNull(message = REQUIRED_FIELD)
    @ApiModelProperty(notes = "Item price in USD",
            required = true)
    private BigDecimal price;

    public Item() {
    }

    public Item(@NotBlank(message = "name" + REQUIRED_FIELD) String name,
                @NotBlank(message = "description" + REQUIRED_FIELD) String description,
                @NotNull(message = "price" + REQUIRED_FIELD) BigDecimal price) {

        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}


