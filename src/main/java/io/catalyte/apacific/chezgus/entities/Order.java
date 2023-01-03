package io.catalyte.apacific.chezgus.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import static io.catalyte.apacific.chezgus.constants.StringConstants.GENERATED_ID;

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


}
