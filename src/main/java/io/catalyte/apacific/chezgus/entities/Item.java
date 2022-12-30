package io.catalyte.apacific.chezgus.entities;

import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
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
}
