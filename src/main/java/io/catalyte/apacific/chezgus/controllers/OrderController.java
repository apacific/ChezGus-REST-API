package io.catalyte.apacific.chezgus.controllers;

import io.catalyte.apacific.chezgus.entities.CustomerOrder;
import io.catalyte.apacific.chezgus.exceptions.ServiceUnavailable;
import io.catalyte.apacific.chezgus.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static io.catalyte.apacific.chezgus.constants.StringConstants.*;

/**
 * CRUD methods for the Order entity
 */
@RestController
@RequestMapping(CONTEXT_ORDERS)
@ApiOperation("Gets responses for 500 errors")
@ApiResponses(value = {@ApiResponse(code = 500, message = UNEXPECTED_SERVER_ERROR,
        response = ServiceUnavailable.class),
        @ApiResponse(code = 503, message = SERVICE_UNAVAILABLE, response = ServiceUnavailable.class)})

public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * Gets all orders if passed a null order or orders matching an example with a non-null
     * customer
     *
     * @return list of orders and status 200
     */
    @GetMapping
    @ApiOperation("Gets all the orders in the database or based on queryable criteria within "
            + "order object")
    @ApiResponses(value = {@ApiResponse(code = 200, message = HTTP_200, responseContainer = "List",
            response = CustomerOrder.class)})
    public ResponseEntity<List<CustomerOrder>> getOrders() {
        logger.info(new Date() + " Get request received for all orders");
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    /**
     * Gets order by id
     *
     * @param id the order's id from the path variable
     * @return the order with same id
     */
    @GetMapping(ID_ENDPOINT)
    @ApiOperation("Gets an order by ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = HTTP_200, response = CustomerOrder.class),
            @ApiResponse(code = 404, message = HTTP_404)})
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable Long id) {
        logger.info(new Date() + " Get by id " + id + " request received");
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    /**
     * Adds new order to the database
     *
     * @param customerOrder the order from the request body being added
     * @return the order if added correctly
     */
    @PostMapping
    @ApiOperation("Adds a new order to the database")
    @ApiResponses(value = {@ApiResponse(code = 201, message = HTTP_201, response = CustomerOrder.class),
            @ApiResponse(code = 400, message = HTTP_400), @ApiResponse(code = 409, message = HTTP_409)})
    public ResponseEntity<CustomerOrder> addOrder(@Valid @RequestBody CustomerOrder customerOrder) {
        logger.info(new Date() + " Post request received");
        return new ResponseEntity<>(orderService.addOrder(customerOrder), HttpStatus.CREATED);
    }

    /**
     * Updates an order within the database
     *
     * @param id    the id of the order to be updated from the path variable
     * @param customerOrder the order's new information from the request body
     * @return the order if correctly updated
     */
    @PutMapping(ID_ENDPOINT)
    @ApiOperation("Updates order by ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = HTTP_200, response = CustomerOrder.class),
            @ApiResponse(code = 400, message = HTTP_400), @ApiResponse(code = 404, message = HTTP_404),
            @ApiResponse(code = 409, message = HTTP_409)})
    public ResponseEntity<CustomerOrder> updateOrderById(@PathVariable Long id,
                                                         @Valid @RequestBody CustomerOrder customerOrder) {
        logger.info(new Date() + " Put request received for id: " + id);
        return new ResponseEntity<>(orderService.updateOrderById(id, customerOrder), HttpStatus.OK);
    }

    /**
     * Deletes an order from the database
     *
     * @param id the id of the order to be deleted from the path variable
     */
    @DeleteMapping(ID_ENDPOINT)
    @ApiOperation("Deletes order with corresponding ID")
    @ApiResponses(value = {@ApiResponse(code = 204, message = HTTP_204),
            @ApiResponse(code = 404, message = HTTP_404), @ApiResponse(code = 409, message = HTTP_409)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        logger.info(new Date() + " Delete request received for id: " + id);
        orderService.deleteOrder(id);
    }
}
