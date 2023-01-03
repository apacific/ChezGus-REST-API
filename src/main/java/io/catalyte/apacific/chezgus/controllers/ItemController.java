package io.catalyte.apacific.chezgus.controllers;

import io.catalyte.apacific.chezgus.entities.Item;
import io.catalyte.apacific.chezgus.services.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static io.catalyte.apacific.chezgus.constants.StringConstants.*;

/**
 * CRUD methods for the Item entity
 */
@RestController
@RequestMapping(CONTEXT_ORDERS + ORDER_ID_ENDPOINT + CONTEXT_ITEMS)
@ApiOperation("Gets responses for 500 errors")
@ApiResponses(value = {@ApiResponse(code = 500, message = UNEXPECTED_SERVER_ERROR,
        response = HttpServerErrorException.ServiceUnavailable.class),
        @ApiResponse(code = 503, message = SERVICE_UNAVAILABLE, response = HttpServerErrorException.ServiceUnavailable.class)})

public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * Get all the items for an order in the database
     *
     * @param orderId the id of the order with items from the path variable
     * @return list of items and status 200
     */
    @GetMapping
    @ApiOperation("Gets from the database all the items based on the order id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = HTTP_200, responseContainer = "List",
            response = Item.class)})
    public ResponseEntity<List<Item>> getItems(@PathVariable Long orderId) {
        logger
                .info(new Date() + " Get request received of items for patient with id: " + orderId);

        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    /**
     * Gets item by id for a order in the database
     *
     * @param orderId the id of the order connected to the item from the path variable
     * @param id        the item's id from the path variable
     * @return the item with the same id
     */
    @GetMapping(value = ID_ENDPOINT)
    @ApiOperation("Gets a item by its ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = HTTP_200, response = Item.class),
            @ApiResponse(code = 404, message = HTTP_404)})
    public ResponseEntity<Item> getItem(@PathVariable Long orderId,
                                        @PathVariable Long id) {
        logger.info(new Date() + " Get by id: " + id + " for order with id: " + orderId);

        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    /**
     * Adds new item to the order
     *
     * @param orderId the id of the order connected to the item from the path variable
     * @param item the item from the request body being added
     * @return the item if added correctly
     */
    @PostMapping
    @ApiOperation("Add a new item to the order")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED", response = Item.class),
            @ApiResponse(code = 400, message = "BAD DATA")})
    public ResponseEntity<Item> addItem(@PathVariable Long orderId,
                                                  @Valid @RequestBody Item item) {
        logger.info(new Date() + "  Item POST request received");

        return new ResponseEntity<>(itemService.addItem(item),
                HttpStatus.CREATED);
    }

    /**
     * Updates an item within the database
     *
     * @param orderId the id of the order connected to the item from the path variable
     * @param id        the id of the item to updated from the path variable
     * @param item the item's new information from the request body
     * @return the item if correctly updated
     */
    @PutMapping(value = ID_ENDPOINT)
    @ApiOperation("Updates item by ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 400, message = "BAD DATA"),
            @ApiResponse(code = 404, message = "NOT FOUND")})
    public ResponseEntity<Item> updateItemById(@PathVariable Long orderId,
                                                         @PathVariable Long id, @Valid @RequestBody Item item) {
        logger.info(new Date() + " Item PUT request received for id: " + id);

        return new ResponseEntity<>(itemService.updateItemById(id, item),
                HttpStatus.OK);
    }
}
