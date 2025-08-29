package ru.micro.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.micro.customer.domain.Customer;

/**
 * @author a.zharov
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
