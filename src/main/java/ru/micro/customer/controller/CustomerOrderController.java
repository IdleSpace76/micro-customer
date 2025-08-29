package ru.micro.customer.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.micro.customer.domain.Customer;
import ru.micro.customer.domain.Order;
import ru.micro.customer.repository.CustomerRepository;

import java.util.Optional;

/**
 * @author a.zharov
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CustomerOrderController {
    private static final String ENTITY_NAME = "order";

    private final CustomerRepository customerRepository;

    public CustomerOrderController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customerOrders/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable String customerId, @Valid @RequestBody Order order) {
        log.debug("REST request to save Order : {} for Customer ID: {}", order, customerId);
        if (customerId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Customer: " + ENTITY_NAME);
        }
        final Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            final var customer = customerOptional.get();
            customer.addOrder(order);
            customerRepository.save(customer);
            return ResponseEntity.ok()
                    .body(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer: " + ENTITY_NAME);
        }
    }
}
